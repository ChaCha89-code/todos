package com.github.chacha89.todos.aop;

import com.github.chacha89.todos.activitylog.entity.ActivityLog;
import com.github.chacha89.todos.activitylog.repository.ActivityLogRepository;
import com.github.chacha89.todos.jwt.service.JWTUtil;
import com.github.chacha89.todos.common.commonEnum.Progress;
import com.github.chacha89.todos.todo.repository.TodoRepository;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class ActivityLogAspect {


    // 속성
    private final ActivityLogRepository activityLogRepository;
    private final JWTUtil jwtUtil;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    // 생성자
    public ActivityLogAspect(ActivityLogRepository activityLogRepository, JWTUtil jwtUtil, TodoRepository todoRepository, UserRepository userRepository) {
        this.activityLogRepository = activityLogRepository;
        this.jwtUtil = jwtUtil;
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }


    // 기능
    @Around(value = "execution(* com.github.chacha89.todos.todo.service.TodoService.*(..))")
    public Object logTodoActivity(ProceedingJoinPoint joinPoint) throws Throwable {

        // 로그 객체 생성
        ActivityLog activityLog = new ActivityLog();

        // 파라미터에서 값 가져오기
        Object[] args = joinPoint.getArgs();

        Long targetId = null;
        /**
         * 작업 ID - 메서드에 전달된 모든 파라미터 하나씩 꺼내서 첫 번째 Long 타입 값을 targetId 변수에 저장
         */

        for (Object arg : args) {
            if (arg instanceof Long) {
                targetId = (Long) arg;
                break;
            }
        }
        activityLog.setTargetId(targetId);

        // updateTodoAPI 실행 전 Progress 상태
        Progress beforeProgress = null;
        String methodName = joinPoint.getSignature().getName();

        if ("updateTodoAPI".equals(methodName) && targetId != null) {
            beforeProgress = todoRepository.findById(targetId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다."))
                    .getProgress();
        }

        // 프로젝트 실행 지점
        Object result = joinPoint.proceed();

        /**
         * Http 요청 정보 꺼내기
         * RequestContextHolder - 현재 요청(request) 정보 담고 있음.
         * getRequestAttributes로 요청 정보 꺼내고, ServletRequestAttributes로 다운캐스팅
         */
        // 1. HTTP 요청 정보 꺼내기
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        // 2. 헤더에서 토큰 추출
        String header = request.getHeader("Authorization");
        String token = header.substring(7);

        // 3. 토큰 검증
        Claims claims = jwtUtil.verifyToken(token);
        log.info("claims: {}", claims);

        // 4. ActivityLog 안에 값 넣어주기

        /**
         * 사용자 ID
         */
        String getUserEmail = claims.get("email", String.class);
        log.info("email claims: {}", claims.get("email"));
        User user = userRepository.findByEmail(getUserEmail)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 사용자가 없습니다."));
        Long userId = user.getId();

        activityLog.setUserId(userId);

        /**
         * 활동 유형
         */
        activityLog.setRequestMethod(methodName);


        /**
         * 변경 내용
         */
        switch (methodName) {
            case "createTodoService":
                String createActivityType = "새로운 할 일이 생성되었습니다.";
                activityLog.setActivityType(createActivityType);
                break;

            case "updateTodoAPI":
                Progress afterProgress = todoRepository.getById(targetId).getProgress();
                if (!beforeProgress.equals(afterProgress)) {
                    String updateActivityProgress = "상태가 " + beforeProgress + "에서 " + afterProgress + "로 변경되었습니다.";
                    activityLog.setActivityType(updateActivityProgress);
                } else {
                    String updateActivityType = "할 일이 변경되었습니다.";
                    activityLog.setActivityType(updateActivityType);
                }
                break;

            case "deleteTodoService":
                String deleteActivityType = "할 일이 삭제되었습니다.";
                activityLog.setActivityType(deleteActivityType);
                break;

            default:
                break;
        }

        /**
         * 요청 IP 주소
         */
        String requestIp = request.getRemoteAddr();
        activityLog.setRequestIp(requestIp);

        /**
         * 요청 URL
         */
        String requestURI = request.getRequestURI();
        activityLog.setRequestUrl(requestURI);


        activityLogRepository.save(activityLog);

        return result;
    }


}

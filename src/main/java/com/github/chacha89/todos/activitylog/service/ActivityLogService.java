package com.github.chacha89.todos.activitylog.service;

import com.github.chacha89.todos.activitylog.dto.ActivityLogDto;
import com.github.chacha89.todos.activitylog.dto.ActivityLogListResponseDto;
import com.github.chacha89.todos.activitylog.entity.ActivityLog;
import com.github.chacha89.todos.activitylog.exception.ActivityLogNotfoundException;
import com.github.chacha89.todos.activitylog.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityLogService {

    // 속성
    private final ActivityLogRepository activityLogRepository;



    // 생성자
    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }


    // 기능
    public ActivityLogListResponseDto getLogService() {
        List<ActivityLog> activityLogList = activityLogRepository.findAll();

        if (activityLogList.isEmpty()) {
            throw new ActivityLogNotfoundException("활동 로그가 없습니다.");
        }

        List<ActivityLogDto> activityLogDtoList = activityLogList.stream()
                .map(activityLog -> new ActivityLogDto(
                        activityLog.getUserId(),
                        activityLog.getTargetId(),
                        activityLog.getActivityType(),
                        activityLog.getRequestUrl(),
                        activityLog.getRequestMethod(),
                        activityLog.getRequestIp(),
                        activityLog.getRequestTime())
                ).collect(Collectors.toList());

        ActivityLogListResponseDto responseDto = new ActivityLogListResponseDto(200, "success", activityLogDtoList);
        return responseDto;
    }
}

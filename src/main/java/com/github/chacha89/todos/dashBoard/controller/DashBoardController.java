package com.github.chacha89.todos.dashboard.controller;

import com.github.chacha89.todos.common.commonEnum.Priority;
import com.github.chacha89.todos.dashboard.service.DashBoardService;
import com.github.chacha89.todos.todo.entity.Todo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {
    private final DashBoardService dashBoardService;

    public DashBoardController(DashBoardService dashBoardService) {
        this.dashBoardService = dashBoardService;
    }

    /**
     * 대쉬보드 전체 수 조회
     */
    @GetMapping()
    public ResponseEntity<String> getTodoAllCountAPI() {
        Long todoAllCountAPI = dashBoardService.getTodoAllCountAPI();
        return ResponseEntity.ok("전체 테스크 수 : " + todoAllCountAPI);
    }

    /**
     * 대쉬보드 특정 상태 개수 조회
     * @param progress
     * @return
     */
    @GetMapping("/getprogress/{progress}")
    public ResponseEntity <String> getProgressCount(@PathVariable String progress){
        Long progressCount = dashBoardService.getProgressCount(progress);
        return ResponseEntity.ok(progress.toUpperCase() + " 테스크 수는 :" + progressCount);
    }

    /**
     * 대시보드 완료율 구하기
     */
    @GetMapping("/donePercent")
    public ResponseEntity<String> getCompletedPercent(){
        double progressPercent = dashBoardService.getProgressPercent();
        return ResponseEntity.ok("완료율은 다음과 같습니다. : " + progressPercent+"%");
    }

    @GetMapping("/taskSummary")
    public ResponseEntity<Map<Priority, List<Todo>>> getTodoSummary(){
        Map<Priority, List<Todo>> todoSummary = dashBoardService.getTodoSummary();
        return ResponseEntity.ok( todoSummary);
    }

}

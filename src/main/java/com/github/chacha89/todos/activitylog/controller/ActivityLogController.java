package com.github.chacha89.todos.activitylog.controller;

import com.github.chacha89.todos.activitylog.dto.ActivityLogListResponseDto;
import com.github.chacha89.todos.activitylog.dto.LogCreateRequestDto;
import com.github.chacha89.todos.activitylog.service.ActivityLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class ActivityLogController {

    // 속성
    private final ActivityLogService activityLogService;



    // 생성자
    public ActivityLogController(ActivityLogService activityLogService) {
        this.activityLogService = activityLogService;
    }


    // 기능
    @GetMapping
    public ResponseEntity<ActivityLogListResponseDto> getLogAPI() {
        ActivityLogListResponseDto responseDto = activityLogService.getLogService();
        ResponseEntity<ActivityLogListResponseDto> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }


}

package com.github.chacha89.todos.todo.controller;

import com.github.chacha89.todos.jwt.service.JWTUtil;

import com.github.chacha89.todos.todo.dto.request.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.response.*;
import com.github.chacha89.todos.todo.dto.request.UpdateTodoRequestDto;
import com.github.chacha89.todos.common.commonEnum.Priority;

import com.github.chacha89.todos.common.commonEnum.Progress;
import com.github.chacha89.todos.todo.entity.Todo;


import com.github.chacha89.todos.todo.service.TodoService;
import com.github.chacha89.todos.user.service.UserService;
import org.springframework.http.HttpStatus;

import io.jsonwebtoken.Claims;
import com.github.chacha89.todos.common.responseDto.APIResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import java.util.Map;


@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final JWTUtil jwtUtil;
    private final UserService userService;


    public TodoController(TodoService todoService, JWTUtil jwtUtil, UserService userService) {
        this.todoService = todoService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    /**
     * 할 일 생성 API
     *
     * @param bearerToken
     * @param requestDto
     * @return
     */
    // 1. Update your createTodoAPI method to accept the Authorization header using @RequestHeader.
    @PostMapping
    public ResponseEntity<TodoCreateResponseDto> createTodoAPI(
            @RequestHeader("Authorization") String bearerToken,
            @ModelAttribute TodoCreateRequestDto requestDto
    ) {
        // 2. Remove the Bearer prefix from the token string:
        String token = bearerToken.replace("Bearer ", "").trim(); // 3. Extract the Token from the Header
        Claims claims = jwtUtil.verifyToken(token); // 4. Validate and parse claims
        Long userId = Long.parseLong(claims.getSubject()); // 5. Extract userId

        // 6. Pass to service
        TodoCreateResponseDto responseDto = todoService.createTodoService(userId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // todo 조회
    // 토큰 적용 추후 추가
    @GetMapping
    public ResponseEntity<TodoListPaginatedResponseDto<GetTodoListResponseDto>>
                    getTodoListAPI(@RequestParam String progress,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(required = false) String search) {

        Progress fromStringToEnum = Progress.fromString(progress);
        TodoListPaginatedResponseDto<GetTodoListResponseDto> todoListService
                = todoService.getTodoListService(fromStringToEnum, page, size, search);
        ResponseEntity<TodoListPaginatedResponseDto<GetTodoListResponseDto>> responseEntity
                = new ResponseEntity<>(todoListService, HttpStatus.OK);
        return responseEntity;
    }

    /**
     * 변경(임마 복사)
     */
    @PatchMapping("/{id}")
    public APIResponse<UpdateTodoRequestDto> updateTodoAPI(@PathVariable Long id,
                                                           @RequestBody UpdateTodoRequestDto updateRequestDto) {

        UpdateTodoRequestDto updateTodoRequestDto = todoService.updateTodoAPI(id, updateRequestDto);
        return APIResponse.success(updateTodoRequestDto, "회원 수정 성공");
    }


    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDetailResponseDto> detailTodoAPI(@PathVariable Long todoId) {
        TodoDetailResponseDto responseDto = todoService.findById(todoId);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 할 일 삭제 API
     *
     * @param todoId
     * @return
     */
    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoDeleteResponseDto> deleteTodoAPI(@PathVariable Long todoId) {
        TodoDeleteResponseDto responseDto = todoService.deleteTodoService(todoId);
        if (responseDto.getStatus() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }

}

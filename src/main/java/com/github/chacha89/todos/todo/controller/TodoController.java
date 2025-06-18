package com.github.chacha89.todos.todo.controller;

import com.github.chacha89.todos.jwt.service.JWTService;
import com.github.chacha89.todos.todo.dto.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
import com.github.chacha89.todos.todo.dto.TodoDeleteResponseDto;
import com.github.chacha89.todos.todo.service.TodoService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final JWTService jwtService;


    public TodoController(TodoService todoService, JWTService jwtService) {
        this.todoService = todoService;
        this.jwtService = jwtService;
    }

    // 1. Update your createTodoAPI method to accept the Authorization header using @RequestHeader.
    @PostMapping
    public ResponseEntity<TodoCreateResponseDto> createTodoAPI(
            @RequestHeader("Authorization") String bearerToken,
            @ModelAttribute TodoCreateRequestDto requestDto
    ) {
        // 2. Remove the Bearer prefix from the token string:
        String token = bearerToken.replace("Bearer ", "").trim(); // 3. Extract the Token from the Header
        Claims claims = jwtService.verifyToken(token); // 4. Validate and parse claims
        Long userId = Long.parseLong(claims.getSubject()); // 5. Extract userId

        // 6. Pass to service
        TodoCreateResponseDto responseDto = todoService.createTodoService(userId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoDeleteResponseDto> deleteTodoAPI(@PathVariable Long todoId) {
        TodoDeleteResponseDto responseDto = todoService.deleteToService(todoId);
        if (responseDto.getStatus() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }
}

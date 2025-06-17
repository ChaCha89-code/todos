package com.github.chacha89.todos.todo.controller;

import com.github.chacha89.todos.todo.dto.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
import com.github.chacha89.todos.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoCreateResponseDto> createTodoAPI(@ModelAttribute TodoCreateRequestDto requestDto) {
        TodoCreateResponseDto responseDto = todoService.createTodoService(requestDto);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping
    public ResponseEntity<String> getTodoListAPI() {
        ResponseEntity<String> responseEntity = new ResponseEntity<>("완성", HttpStatus.OK);
        return responseEntity;
    }
}

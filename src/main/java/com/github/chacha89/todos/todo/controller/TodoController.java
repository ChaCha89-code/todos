package com.github.chacha89.todos.todo.controller;

import com.github.chacha89.todos.todo.dto.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
import com.github.chacha89.todos.todo.dto.response.dto.dto.response.GetTodoListResponseDto;
import com.github.chacha89.todos.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<GetTodoListResponseDto>> getTodoListAPI() {
        List<GetTodoListResponseDto> todoListService = todoService.getTodoListService();
        ResponseEntity<List<GetTodoListResponseDto>> responseEntity
                = new ResponseEntity<>(todoListService, HttpStatus.OK);
        return responseEntity;
    }
}

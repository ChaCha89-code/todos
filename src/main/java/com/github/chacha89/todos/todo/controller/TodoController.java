package com.github.chacha89.todos.todo.controller;

import com.github.chacha89.todos.todo.dto.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
import com.github.chacha89.todos.todo.dto.TodoDeleteResponseDto;
import com.github.chacha89.todos.todo.dto.response.dto.dto.response.TodoDetailResponseDto;
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

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDetailResponseDto> detailTodoAPI(@PathVariable Long todoId) {
        TodoDetailResponseDto responseDto = todoService.findById(todoId);
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

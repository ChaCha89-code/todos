package com.github.chacha89.todos.todo.controller;

import com.github.chacha89.todos.todo.dto.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
import com.github.chacha89.todos.todo.dto.UpdateTodoRequestDto;
import com.github.chacha89.todos.todo.service.TodoService;
import com.github.chacha89.todos.user.dto.responseDto.APIResponse;
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



    /**
     * 변경(임마 복사)
     */
    @PatchMapping("/{id}")
    public APIResponse <UpdateTodoRequestDto> updateTodoAPI(@PathVariable Long id,
                              @RequestBody UpdateTodoRequestDto updateRequestDto){
        UpdateTodoRequestDto updateTodoRequestDto = todoService.updateTodoAPI(id, updateRequestDto);
        return APIResponse.success( updateTodoRequestDto, "회원 수정 성공");
    }


}

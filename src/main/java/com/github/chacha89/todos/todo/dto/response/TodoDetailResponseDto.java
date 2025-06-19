package com.github.chacha89.todos.todo.dto.response;

import com.github.chacha89.todos.todo.entity.Priority;
import com.github.chacha89.todos.todo.entity.Progress;
import com.github.chacha89.todos.todo.entity.Todo;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Getter
public class TodoDetailResponseDto {


    private Long userId;
    private String title;
    private String image;
    private String content;
    private String assignee;
    private Priority priority;
    private Progress progress;
    private LocalDate dueDate;

    public TodoDetailResponseDto(
            Long userId,
            String title,
            String image,
            String content,
            String assignee,
            Priority priority,
            Progress progress,
            LocalDate dueDate
    ) {
        this.userId = userId;
        this.title = title;
        this.image = image;
        this.content = content;
        this.assignee = assignee;
        this.priority = priority;
        this.progress = progress;
        this.dueDate = dueDate;
    }


}

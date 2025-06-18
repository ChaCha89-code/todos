package com.github.chacha89.todos.todo.dto.response.dto.dto.response;

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
    private String priority;
    private String progress;
    private LocalDate dueDate;

    public TodoDetailResponseDto(
            Long userId,
            String title,
            String image,
            String content,
            String assignee,
            String priority,
            String progress,
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

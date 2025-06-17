package com.github.chacha89.todos.todo.dto.response.dto.dto.request;

import lombok.Getter;
import org.springframework.context.i18n.LocaleContextThreadLocalAccessor;

import java.time.LocalDate;


@Getter
public class CreateTodoRequestDto {
    private String totalContents;
    private Long userId;
    private String todoImage;
    private String assignee;
    private String priority;
    private String progress;
    private LocalDate duedate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}

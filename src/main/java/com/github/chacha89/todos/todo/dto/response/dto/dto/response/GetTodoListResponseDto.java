package com.github.chacha89.todos.todo.dto.response.dto.dto.response;


import com.github.chacha89.todos.todo.entity.Priority;
import com.github.chacha89.todos.todo.entity.Priority;
import com.github.chacha89.todos.todo.entity.Progress;
import com.github.chacha89.todos.todo.entity.Todo;

import lombok.ToString;


import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
public class GetTodoListResponseDto {
//    private final Progress progressStatus;
//    private final String message;
//    public todoList;
//    public Progress inProgress;
//    public Progress done;
//    public Progress overdue;
//
//    public GetTodoListResponseDto(Todo TodoList) {
//        this.progressStatus = Progress.Todo;
//        this.message = "성공";
//        this.todoList =
//    }
//
//    public GetTodoListResponseDto(Todo inProgress) {
//        this.progressStatus = Progress.InProgress;
//        this.message = "성공";
//        this.inProgress = inProgress.getProgress();
//    }
//
//    public GetTodoListResponseDto(Todo done) {
//        this.progressStatus = Progress.Done;
//        this.message = "성공";
//        this.done = done.getProgress();
//    }
//
//    public GetTodoListResponseDto(Todo overdue) {
//        this.progressStatus = Progress.OverDue;
//        this.message = "성공";
//        this.overdue = overdue.getProgress();
//    }
//
//    public Progress getProgressStatus() {
//        return progressStatus;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//
//    public static class TodoList {
//
//        private final Long id;
//        private final String title;
//        private final String image;
//        private final String todoContent;
//        private final String assignee;
//        private final Priority priority;
//        private final LocalDate dueDate;
//        private final LocalDateTime createAt;
//        private final LocalDateTime updateAt;
//
//        public TodoList(Todo todo) {
//            this.id = todo.getId();
//            this.title = todo.getTitle();
//            this.image = todo.getImage();
//            this.todoContent = todo.getContent();
//            this.assignee = todo.getAssignee();
//            this.priority = todo.getPriority();
//            this.dueDate = todo.getDueDate();
//            this.createAt = todo.getCreatedAt();
//            this.updateAt = todo.getUpdatedAt();
//        }
//
//        public Long getId() {
//            return id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public String getTodoContent() {
//            return todoContent;
//        }
//
//        public String getAssignee() {
//            return assignee;
//        }
//
//        public Priority getPriority() {
//            return priority;
//        }
//
//        public LocalDate getDueDate() {
//            return dueDate;
//        }
//
//        public LocalDateTime getCreateAt() {
//            return createAt;
//        }
//
//        public LocalDateTime getUpdateAt() {
//            return updateAt;
//        }
//    }
//
//    public static class InProgressList {
//
//        private final Long id;
//        private final String title;
//        private final String image;
//        private final String todoContent;
//        private final String assignee;
//        private final Priority priority;
//        private final LocalDate dueDate;
//        private final LocalDateTime createAt;
//        private final LocalDateTime updateAt;
//
//        public InProgressList(Todo todo) {
//            this.id = todo.getId();
//            this.title = todo.getTitle();
//            this.image = todo.getImage();
//            this.todoContent = todo.getContent();
//            this.assignee = todo.getAssignee();
//            this.priority = todo.getPriority();
//            this.dueDate = todo.getDueDate();
//            this.createAt = todo.getCreatedAt();
//            this.updateAt = todo.getUpdatedAt();
//        }
//
//
//        public Long getId() {
//            return id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public String getTodoContent() {
//            return todoContent;
//        }
//
//        public String getAssignee() {
//            return assignee;
//        }
//
//        public Priority getPriority() {
//            return priority;
//        }
//
//        public LocalDate getDueDate() {
//            return dueDate;
//        }
//
//        public LocalDateTime getCreateAt() {
//            return createAt;
//        }
//
//        public LocalDateTime getUpdateAt() {
//            return updateAt;
//        }
//    }
//
//    public static class DoneList {
//
//        private final Long id;
//        private final String title;
//        private final String image;
//        private final String todoContent;
//        private final String assignee;
//        private final Priority priority;
//        private final LocalDate dueDate;
//        private final LocalDateTime createAt;
//        private final LocalDateTime updateAt;
//
//        public DoneList(Todo todo) {
//
//            this.id = todo.getId();
//            this.title = todo.getTitle();
//            this.image = todo.getImage();
//            this.todoContent = todo.getContent();
//            this.assignee = todo.getAssignee();
//            this.priority = todo.getPriority();
//            this.dueDate = todo.getDueDate();
//            this.createAt = todo.getCreatedAt();
//            this.updateAt = todo.getUpdatedAt();
//        }
//
//
//        public Long getId() {
//            return id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public String getTodoContent() {
//            return todoContent;
//        }
//
//        public String getAssignee() {
//            return assignee;
//        }
//
//        public Priority getPriority() {
//            return priority;
//        }
//
//        public LocalDate getDueDate() {
//            return dueDate;
//        }
//
//        public LocalDateTime getCreateAt() {
//            return createAt;
//        }
//
//        public LocalDateTime getUpdateAt() {
//            return updateAt;
//        }
//    }
//
//    public static class OverdueList {
//
//        private final Long id;
//        private final String title;
//        private final String image;
//        private final String todoContent;
//        private final String assignee;
//        private final Priority priority;
//        private final LocalDate dueDate;
//        private final LocalDateTime createAt;
//        private final LocalDateTime updateAt;
//
//        public OverdueList(Todo todo) {
//
//            this.id = todo.getId();
//            this.title = todo.getTitle();
//            this.image = todo.getImage();
//            this.todoContent = todo.getContent();
//            this.assignee = todo.getAssignee();
//            this.priority = todo.getPriority();
//            this.dueDate = todo.getDueDate();
//            this.createAt = todo.getCreatedAt();
//            this.updateAt = todo.getUpdatedAt();
//        }
//
//
//        public Long getId() {
//            return id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getImage() {
//            return image;
//        }
//
//        public String getTodoContent() {
//            return todoContent;
//        }
//
//        public String getAssignee() {
//            return assignee;
//        }
//
//        public Priority getPriority() {
//            return priority;
//        }
//
//        public LocalDate getDueDate() {
//            return dueDate;
//        }
//
//        public LocalDateTime getCreateAt() {
//            return createAt;
//        }
//
//        public LocalDateTime getUpdateAt() {
//            return updateAt;
//        }
//    }
//

}

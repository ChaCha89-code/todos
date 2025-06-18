package com.github.chacha89.todos.todo.dto.response.dto.dto.response;


import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.todo.progressStatus.ProgressStatus;
import lombok.ToString;


import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
public class GetTodoListResponseDto {
    private final ProgressStatus progressStatus;
    private final String message;
    public TodoList todoList;
    public OnProgressList inProgress;
    public DoneList done;
    public OverdueList overdue;

    public GetTodoListResponseDto(TodoList todoList) {
        this.progressStatus = ProgressStatus.TODO;
        this.message = "성공";
        this.todoList = todoList;
    }

    public GetTodoListResponseDto(OnProgressList inProgress) {
        this.progressStatus = ProgressStatus.INPROGRESS;
        this.message = "성공";
        this.inProgress = inProgress;
    }

    public GetTodoListResponseDto(DoneList done) {
        this.progressStatus = ProgressStatus.DONE;
        this.message = "성공";
        this.done = done;
    }

    public GetTodoListResponseDto(OverdueList overdue) {
        this.progressStatus = ProgressStatus.OVERDUE;
        this.message = "성공";
        this.overdue = overdue;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public String getMessage() {
        return message;
    }


    public static class TodoList {

        private final Long id;
        private final String title;
        private final String image;
        private final String todoContent;
        private final String assignee;
        private final String priority;
        private final LocalDate dueDate;
        private final LocalDateTime createAt;
        private final LocalDateTime updateAt;

        public TodoList(Todo todo) {
            this.id = todo.getId();
            this.title = todo.getTitle();
            this.image = todo.getImage();
            this.todoContent = todo.getContent();
            this.assignee = todo.getAssignee();
            this.priority = todo.getPriority();
            this.dueDate = todo.getDueDate();
            this.createAt = todo.getCreatedAt();
            this.updateAt = todo.getUpdatedAt();
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getTodoContent() {
            return todoContent;
        }

        public String getAssignee() {
            return assignee;
        }

        public String getPriority() {
            return priority;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public LocalDateTime getCreateAt() {
            return createAt;
        }

        public LocalDateTime getUpdateAt() {
            return updateAt;
        }
    }

    public static class OnProgressList {

        private final Long id;
        private final String title;
        private final String image;
        private final String todoContent;
        private final String assignee;
        private final String priority;
        private final LocalDate dueDate;
        private final LocalDateTime createAt;
        private final LocalDateTime updateAt;

        public OnProgressList(Todo todo) {
            this.id = todo.getId();
            this.title = todo.getTitle();
            this.image = todo.getImage();
            this.todoContent = todo.getContent();
            this.assignee = todo.getAssignee();
            this.priority = todo.getPriority();
            this.dueDate = todo.getDueDate();
            this.createAt = todo.getCreatedAt();
            this.updateAt = todo.getUpdatedAt();
        }


        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getTodoContent() {
            return todoContent;
        }

        public String getAssignee() {
            return assignee;
        }

        public String getPriority() {
            return priority;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public LocalDateTime getCreateAt() {
            return createAt;
        }

        public LocalDateTime getUpdateAt() {
            return updateAt;
        }
    }

    public static class DoneList {

        private final Long id;
        private final String title;
        private final String image;
        private final String todoContent;
        private final String assignee;
        private final String priority;
        private final LocalDate dueDate;
        private final LocalDateTime createAt;
        private final LocalDateTime updateAt;

        public DoneList(Todo todo) {

            this.id = todo.getId();
            this.title = todo.getTitle();
            this.image = todo.getImage();
            this.todoContent = todo.getContent();
            this.assignee = todo.getAssignee();
            this.priority = todo.getPriority();
            this.dueDate = todo.getDueDate();
            this.createAt = todo.getCreatedAt();
            this.updateAt = todo.getUpdatedAt();
        }


        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getTodoContent() {
            return todoContent;
        }

        public String getAssignee() {
            return assignee;
        }

        public String getPriority() {
            return priority;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public LocalDateTime getCreateAt() {
            return createAt;
        }

        public LocalDateTime getUpdateAt() {
            return updateAt;
        }
    }

    public static class OverdueList {

        private final Long id;
        private final String title;
        private final String image;
        private final String todoContent;
        private final String assignee;
        private final String priority;
        private final LocalDate dueDate;
        private final LocalDateTime createAt;
        private final LocalDateTime updateAt;

        public OverdueList(Todo todo) {

            this.id = todo.getId();
            this.title = todo.getTitle();
            this.image = todo.getImage();
            this.todoContent = todo.getContent();
            this.assignee = todo.getAssignee();
            this.priority = todo.getPriority();
            this.dueDate = todo.getDueDate();
            this.createAt = todo.getCreatedAt();
            this.updateAt = todo.getUpdatedAt();
        }


        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getTodoContent() {
            return todoContent;
        }

        public String getAssignee() {
            return assignee;
        }

        public String getPriority() {
            return priority;
        }

        public LocalDate getDueDate() {
            return dueDate;
        }

        public LocalDateTime getCreateAt() {
            return createAt;
        }

        public LocalDateTime getUpdateAt() {
            return updateAt;
        }
    }


}

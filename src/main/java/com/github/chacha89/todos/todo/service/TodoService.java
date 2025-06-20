package com.github.chacha89.todos.todo.service;

import com.github.chacha89.todos.todo.exception.TodoCreateException;
import com.github.chacha89.todos.user.exception.UserIdNotFoundException;
import com.github.chacha89.todos.todo.dto.request.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.response.*;
import com.github.chacha89.todos.todo.dto.request.UpdateTodoRequestDto;
import com.github.chacha89.todos.common.commonEnum.Priority;
import com.github.chacha89.todos.common.commonEnum.Progress;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.todo.repository.TodoRepository;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    @Value("${file.path}")
    private String uploadFolder;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    /**
     * 할일 생성 기능
     * @param userId
     * @param requestDto
     * @return
     */
    @Transactional
    public TodoCreateResponseDto createTodoService(Long userId, TodoCreateRequestDto requestDto) {

        // 1. 데이터 준비
        String title = requestDto.getTitle();
        MultipartFile image = requestDto.getImage();
        String todoContents = requestDto.getTodoContents();
        String assignee = requestDto.getAssignee();
        LocalDate dueDate = requestDto.getDueDate();


        Priority priority;
        Progress progress;

        // 문자열을 enum으로 바꿔주는 과정 & 예외 처리
        try {
            priority = Priority.valueOf(requestDto.getPriority()); // TodoService : valueOf() + exception handeling 사용 -> .isEnum 사용 안해도 됨
        } catch (IllegalArgumentException e) {
            throw new TodoCreateException(400, "올바르지 않은 우선순위입니다. (Low, Medium, High 중 선택)");
        }

        try {
            progress = Progress.valueOf(requestDto.getProgress());
        } catch (IllegalArgumentException e) {
            throw new TodoCreateException(400, "올바르지 않은 진행상태입니다. (NotStarted, InProgress, Completed 중 선택)");
        }


        // 사진파일 준비
        String url = null;
        if (image != null) {
            UUID uuid = UUID.randomUUID();
            String imageFileName = uuid + "_" + requestDto.getImage().getOriginalFilename();
            System.out.println("이미지 파일 이름: " + imageFileName);
            url = uploadFolder + imageFileName;
            Path imageFilePath = Paths.get(url); // String -> Path

            // The code below writes data(image bytes) to the path represented by imageFilePath.
            try {
                Files.write(imageFilePath, requestDto.getImage().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // 예외 처리

        if(title == null || title.isBlank()
                || todoContents == null || todoContents.isBlank()
                || assignee == null || assignee.isBlank()
                || dueDate == null || dueDate.isBefore(LocalDate.now()))
        {
            throw new TodoCreateException(400, "필수 항목 중 빈 항목이 있거나 마감일이 잘못 설정되었습니다.");

        }

        User foundUser = userRepository.findById(userId).
                orElseThrow(() -> new TodoCreateException(404, "사용자ID가 존재하지 않습니다."));

        Todo newTodo = new Todo(foundUser, assignee, title, url, todoContents, priority, progress, dueDate);

        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoCreateResponseDto(
                savedTodo.getTitle(),
                savedTodo.getImage(),
                savedTodo.getContent(),
                foundUser.getId(),
                savedTodo.getId(),
                savedTodo.getAssignee(),
                savedTodo.getPriority().name(),
                savedTodo.getProgress().name(),
                savedTodo.getDueDate(),
                savedTodo.getCreatedAt(),
                savedTodo.getUpdatedAt()
        );
    }

    /**
     * 할 일 단건 조회 기능
     * @param todoId
     * @return
     */
    @Transactional
    public TodoDetailResponseDto findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new UserIdNotFoundException(HttpStatus.NOT_FOUND));
        return new TodoDetailResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getImage(),
                todo.getContent(),
                todo.getAssignee(),
                todo.getPriority(),
                todo.getProgress(),
                todo.getDueDate()
        );
    }

    /**
     * 할 일 수정(상태 변경) 기능
     */
    @Transactional
    public UpdateTodoRequestDto updateTodoAPI(Long id, UpdateTodoRequestDto updateRequestDto) {
        //1. 데이터 준비
        Todo todo = todoRepository.findById(id).orElseThrow();

        String newTitle = updateRequestDto.getTitle();
        String newContents = updateRequestDto.getContents();
        String newAssignee = updateRequestDto.getAssignee();
        String newPriority = updateRequestDto.getPriority();
        String newProgress = updateRequestDto.getProgress();
        String newImage = updateRequestDto.getImage();

        //2. 변경 -> null이 아니면  변경

        //제목변경
        if (!(newTitle == null) && !newTitle.isBlank()) {
            todo.changeTitle(newTitle);
        }
        //내용변경
        if (!(newContents == null) && !newContents.isBlank()) {
            todo.changeContent(newContents);
        }
        //담당자 변경
        if (!(newAssignee == null) && !newAssignee.isBlank()) {
            todo.changeAssignee(newAssignee);
        }
        //이미지 변경
        if (!(newImage == null) && !newImage.isBlank()) {
            todo.changeImage(newImage);
        }
        //우선순위 변경
        // todo.getPriority()는 이미 Priority enum인데, Priority.valueOf(...)는 String을 받습니다.
        // 즉, valueOf(enum)은 잘못된 호출이므로 아래와 같이 수정했습니다.
        Priority priority = Priority.LOW;

        if (newPriority != null && !newPriority.isBlank()) {
            try {
                Priority newEnumPriority = Priority.valueOf(newPriority);
                if (newEnumPriority.getLevel() > todo.getPriority().getLevel()) {
                    todo.changePriority(newEnumPriority);

                } else {
                    throw new TodoCreateException(400, "이전 단계로 돌아갈 수 없습니다.");
                }
            } catch (IllegalArgumentException e) {
                throw new TodoCreateException(400, "잘못된 우선순위입니다.");
            }
        }
        //진행상황 변경

        if (newProgress != null && !newProgress.isBlank()) {
            try {
                Progress newEnumProgress = Progress.valueOf(newProgress);
                if (newEnumProgress.getSteps() > todo.getProgress().getSteps()) {
                    todo.changeProgress(newEnumProgress);

                } else {
                    throw new TodoCreateException(400, "이전 단계로 돌아갈 수 없습니다.");
                }

            } catch(IllegalArgumentException e) {
                throw new TodoCreateException(400, "잘못된 우선순위입니다.");
            }
        }
        Todo updatedTodo = todoRepository.save(todo);

        UpdateTodoRequestDto updateTodoResponse = UpdateTodoRequestDto.builder()
                .title(updatedTodo.getTitle())
                .image(updatedTodo.getImage())
                .assignee(updatedTodo.getAssignee())
                .contents(updatedTodo.getContent())
                .priority(updatedTodo.getPriority().name())
                .progress(updatedTodo.getProgress().name())
                .build();

        return updateTodoResponse;
    }

    /**
     * 할 일 삭제 기능
     * @param todoId
     * @return
     */
    @Transactional
    public TodoDeleteResponseDto deleteTodoService(Long todoId) {

        // 데이터 준비
        Optional<Todo> todoOptional = todoRepository.findById(todoId);

        // 검증 로직
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();

            if (todo.getIsDeleted()) {
                return new TodoDeleteResponseDto(400, "이미 삭제된 할 일입니다.");
            }

            todo.setDeleted(true);

            todo.setDeletedAt(LocalDateTime.now());

            todoRepository.save(todo);

            TodoDeleteResponseDto responseDto = new TodoDeleteResponseDto(200, "할 일이 성공적으로 삭제되었습니다.");
            return responseDto;
        } else {
            TodoDeleteResponseDto responseDto = new TodoDeleteResponseDto(404, "할 일이 존재하지 않습니다.");
            return responseDto;
        }
    }

    /**
     * 할 일 전체 조회 기능
     *
     * @param progress
     * @param
     * @param page
     * @param size
     * @param content
     * @return
     */
    @Transactional
    public TodoListPaginatedResponseDto<TodoGetListResponseDto>  getTodoListService(Progress progress, int page,
                                                                                    int size, String content) {
        // 데이터 준비
        Pageable pageable = PageRequest.of(page, size);

        Page<Todo> todoListPageFromTodo
                = todoRepository.findByContentContainingAndProgressOrderByUpdatedAtDesc(content, progress, pageable);

        List<TodoGetListResponseDto> todoGetListResponseDto = todoListPageFromTodo
                .stream()
                .map(Todo -> TodoGetListResponseDto.getTodoListResponseDto(Todo)).toList();

        //반환 준비
        TodoListPaginatedResponseDto<TodoGetListResponseDto> paginatedResponseDto
                = new TodoListPaginatedResponseDto<>(todoGetListResponseDto,
                todoListPageFromTodo.getTotalElements(),
                todoListPageFromTodo.getTotalPages(), size);
        return paginatedResponseDto;
    }
}

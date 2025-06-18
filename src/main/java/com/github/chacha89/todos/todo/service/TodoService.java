package com.github.chacha89.todos.todo.service;

import com.github.chacha89.todos.exception.TodoCreateException;
import com.github.chacha89.todos.todo.dto.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
import com.github.chacha89.todos.todo.dto.response.dto.dto.response.GetTodoListResponseDto;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.todo.repository.TodoRepository;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Transactional
    public TodoCreateResponseDto createTodoService(TodoCreateRequestDto requestDto) {

        // 1. 데이터 준비
        Long userId = requestDto.getUserId();
        String title = requestDto.getTitle();
        MultipartFile image = requestDto.getImage();
        String todoContents = requestDto.getTodoContents();
        String assignee = requestDto.getAssignee();
        String priority = requestDto.getPriority();
        String progress = requestDto.getProgress();
        LocalDate dueDate = requestDto.getDueDate();


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

        if (title.isBlank() || title == null
                || todoContents.isBlank() || todoContents == null
                || assignee.isBlank() || assignee == null
                || priority.isBlank() || priority == null
                || progress.isBlank() || progress == null
                || dueDate.isBefore(LocalDate.now())) {
            throw new TodoCreateException(400, "필수 항목 중 빈 항목이 있거나 마감일이 잘못 설정되었습니다. 다시 확인해주세요.");
        }

        User foundUser = userRepository.findById(userId).
                orElseThrow(() -> new TodoCreateException(404, "존재하지 않는 사용자입니다."));

        Todo newTodo = new Todo(foundUser, title, url, todoContents, assignee, priority, progress, dueDate);

        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoCreateResponseDto(
                savedTodo.getTitle(),
                savedTodo.getImage(),
                savedTodo.getContent(),
                foundUser.getId(),
                savedTodo.getAssignee(),
                savedTodo.getPriority(),
                savedTodo.getProgress(),
                savedTodo.getDueDate(),
                savedTodo.getCreatedAt(),
                savedTodo.getUpdatedAt()
        );

    }

    @Transactional
    public List<GetTodoListResponseDto> getTodoListService(String progress, String username,int page, int size) {
        // 데이터 준비
        Pageable pageable = PageRequest.of(page , size);
        Page<Todo> progressPage = todoRepository.findByProgressAndUser_UserNameOrderByUpdatedAtDesc(progress, username, pageable);

        // 컬렉션 리스트로 만들 깡통 준비
        List<GetTodoListResponseDto> todoDtoList = new ArrayList<>();
        // @Param progress = todo, onprogress, done, overdue 에 맞춰 데이터 반환
        switch (progress) {
            case "todo":
                for (Todo todo : progressPage) {
                    GetTodoListResponseDto todoList
                            = new GetTodoListResponseDto(new GetTodoListResponseDto.TodoList(todo));
                    todoDtoList.add(todoList);
                }
                break;
            case "onprogress":
                for (Todo todo : progressPage) {
                    GetTodoListResponseDto todoList
                            = new GetTodoListResponseDto(new GetTodoListResponseDto.OnProgressList(todo));
                    todoDtoList.add(todoList);
                }
                break;
            case "done":
                for (Todo todo : progressPage) {
                    GetTodoListResponseDto todoList
                            = new GetTodoListResponseDto(new GetTodoListResponseDto.DoneList(todo));
                    todoDtoList.add(todoList);
                }
                break;
            case "overdue":
                for (Todo todo : progressPage) {
                    GetTodoListResponseDto todoList
                            = new GetTodoListResponseDto(new GetTodoListResponseDto.OverdueList(todo));
                    todoDtoList.add(todoList);
                }
                break;
        }

        log.info("responseDtoList: {}", todoDtoList);
        return todoDtoList;

    }


}

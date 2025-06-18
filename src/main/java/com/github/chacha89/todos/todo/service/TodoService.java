package com.github.chacha89.todos.todo.service;

import com.github.chacha89.todos.exception.MissingSearchTermException;
import com.github.chacha89.todos.exception.TodoCreateException;
import com.github.chacha89.todos.todo.dto.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
import com.github.chacha89.todos.todo.dto.response.dto.dto.response.GetTodoListResponseDto;
import com.github.chacha89.todos.todo.dto.TodoDeleteResponseDto;
import com.github.chacha89.todos.todo.dto.UpdateTodoRequestDto;
import com.github.chacha89.todos.todo.entity.Priority;
import com.github.chacha89.todos.todo.entity.Progress;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.todo.progressStatus.ProgressStatus;
import com.github.chacha89.todos.todo.repository.TodoRepository;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
    public TodoCreateResponseDto createTodoService(Long userId, TodoCreateRequestDto requestDto) {

        // 1. 데이터 준비
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

        // 예외 처리
        if (title.isBlank() || title == null
                || todoContents.isBlank() || todoContents == null
                || assignee.isBlank() || assignee == null
                || priority.isBlank() || priority == null
                || progress.isBlank() || progress == null
                || dueDate.isBefore(LocalDate.now())) {
            throw new TodoCreateException(400, "필수 항목 중 빈 항목이 있거나 마감일이 잘못 설정되었습니다. 다시 확인해주세요.");
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
                savedTodo.getAssignee(),
                savedTodo.getPriority(),
                savedTodo.getProgress(),
                savedTodo.getDueDate(),
                savedTodo.getCreatedAt(),
                savedTodo.getUpdatedAt()
        );

    }

    /**
     * 수정
     */
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
        Priority priority = Priority.Low;
        if (!(newPriority == null) && !newPriority.isBlank()) {
            //받아온 값이 enum 인지 체크
            if (priority.isEnum(newPriority)) {
                //newPrioriy가 더 높은 단계의 enum 값이면 변경
                if (Priority.valueOf(newPriority).getLevel() > Priority.valueOf(todo.getPriority()).getLevel()) {
                    todo.changePriority(newPriority);

                } else {
                    throw new TodoCreateException(400, "이전 단계로 돌아갈 수 없습니다.");
                }
            }
        }
        //진행상황 변경
        if (!(newProgress == null) && !newProgress.isBlank()) {
            //enum체크
            Progress progress = Progress.NotStarted;
            //받아온 값이 enum 인지 체크
            if (progress.isEnum(newProgress)) {
                //newPrioriy가 더 높은 단계의 enum 값이면 변경
                if (Progress.valueOf(newProgress).getSteps() > Progress.valueOf(todo.getProgress()).getSteps()) {
                    todo.changePriority(newProgress);

                } else {
                    throw new TodoCreateException(400, "이전 단계로 돌아갈 수 없습니다.");
                }
            }
        }

        Todo updatedTodo = todoRepository.save(todo);

        UpdateTodoRequestDto updateTodoResponse = UpdateTodoRequestDto.builder()
                .title(updatedTodo.getTitle())
                .image(updatedTodo.getImage())
                .assignee(updatedTodo.getAssignee())
                .contents(updatedTodo.getContent())
                .priority(updatedTodo.getPriority())
                .progress(updatedTodo.getProgress())
                .build();

        return updateTodoResponse;
    }

    public TodoDeleteResponseDto deleteToService(Long todoId) {

        // 데이터 준비
        Optional<Todo> todoOptional = todoRepository.findById(todoId);

        // 검증 로직
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todoRepository.delete(todo);
            TodoDeleteResponseDto responseDto = new TodoDeleteResponseDto(200, "댓글이 성공적으로 삭제되었습니다.");
            return responseDto;
        } else {
            TodoDeleteResponseDto responseDto = new TodoDeleteResponseDto(404, "댓글이 존재하지 않습니다.");
            return responseDto;
        }


    }

    /**
     * 조회 기능
     *
     * @param progress
     * @param username
     * @param page
     * @param size
     * @param content
     * @return
     */
    @Transactional
    public List<GetTodoListResponseDto> getTodoListService(String progress, String username, int page, int size, String content) {
        // 데이터 준비
        log.info("content : {}",content);
        log.info("progress : {}",progress);
        log.info("username : {}",username);

        Pageable pageable = PageRequest.of(page, size);
        Page<Todo> progressPage
                = todoRepository.findByContentContainingAndProgressAndUser_UserNameOrderByUpdatedAtDesc
                (content, progress, username, pageable);
//        Page<Todo> todoPage = todoRepository.findByProgressAndUser_UserNameOrderByUpdatedAtDesc(progress, username, pageable);


        // 컬렉션 리스트로 만들 깡통 준비

//        if (search == null || search.isEmpty()) {
//            throw new MissingSearchTermException();
//        }
        List<GetTodoListResponseDto> todoDtoList = new ArrayList<>();

        // @Param progress = todo, onprogress, done, overdue 에 맞춰 데이터 반환
        switch (progress) {

            case "todo":
                for (Todo todo : progressPage) {
                    log.info("progress: {}",progress);
                    log.info("progressPage: {}",progressPage);
                    GetTodoListResponseDto todoList
                            = new GetTodoListResponseDto(new GetTodoListResponseDto.TodoList(todo));
                    log.info("todoList: {}",todoList);
                    todoDtoList.add(todoList);
                    log.info("todoDtoList : {}",todoDtoList);
                }
                break;
            case "inprogress":
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
                    log.info("todoDtoList.add(todoList) : {}",todoDtoList.add(todoList));
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
        log.info("todoDtoList 반환: {}", todoDtoList);
        return todoDtoList;

    }


}

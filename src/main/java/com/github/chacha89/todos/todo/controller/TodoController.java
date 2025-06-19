package com.github.chacha89.todos.todo.controller;

import com.github.chacha89.todos.jwt.service.JWTService;
import com.github.chacha89.todos.todo.dto.TodoCreateRequestDto;
import com.github.chacha89.todos.todo.dto.TodoCreateResponseDto;
import com.github.chacha89.todos.todo.dto.response.dto.dto.response.GetTodoListResponseDto;
import com.github.chacha89.todos.todo.dto.UpdateTodoRequestDto;
import com.github.chacha89.todos.todo.dto.TodoDeleteResponseDto;
import com.github.chacha89.todos.todo.dto.response.dto.dto.response.TodoDetailResponseDto;
import com.github.chacha89.todos.todo.entity.Progress;
import com.github.chacha89.todos.todo.service.TodoService;
import com.github.chacha89.todos.user.service.UserService;
import org.apache.coyote.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;

import io.jsonwebtoken.Claims;
import com.github.chacha89.todos.user.dto.responseDto.APIResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final JWTService jwtService;
    private final UserService userService;


    public TodoController(TodoService todoService, JWTService jwtService, UserService userService) {
        this.todoService = todoService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    /**
     * 할 일 생성 API
     * @param bearerToken
     * @param requestDto
     * @return
     */
    // 1. Update your createTodoAPI method to accept the Authorization header using @RequestHeader.
    @PostMapping
    public ResponseEntity<TodoCreateResponseDto> createTodoAPI(
            @RequestHeader("Authorization") String bearerToken,
            @ModelAttribute TodoCreateRequestDto requestDto
    ) {
        // 2. Remove the Bearer prefix from the token string:
        String token = bearerToken.replace("Bearer ", "").trim(); // 3. Extract the Token from the Header
        Claims claims = jwtService.verifyToken(token); // 4. Validate and parse claims
        Long userId = Long.parseLong(claims.getSubject()); // 5. Extract userId

        // 6. Pass to service
        TodoCreateResponseDto responseDto = todoService.createTodoService(userId, requestDto);
        return ResponseEntity.ok(responseDto);
    }
    // todo 조회
    // 토큰 적용 추후 추가
    @GetMapping
    public ResponseEntity<List<GetTodoListResponseDto>> getTodoListAPI(@RequestParam String progress,
                                                                       @RequestParam String username,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size,
                                                                       @RequestParam(required = false) String search) {
        List<GetTodoListResponseDto> todoListService
                = todoService.getTodoListService(progress,username,page,size,search);
        ResponseEntity<List<GetTodoListResponseDto>> responseEntity
                = new ResponseEntity<>(todoListService, HttpStatus.OK);
        return responseEntity;
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



    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDetailResponseDto> detailTodoAPI(@PathVariable Long todoId) {
        TodoDetailResponseDto responseDto = todoService.findById(todoId);
        return ResponseEntity.ok(responseDto);
    }


    /**
     * 할 일 삭제 API
     * @param todoId
     * @return
     */
    @DeleteMapping("/{todoId}")
    public ResponseEntity<TodoDeleteResponseDto> deleteTodoAPI(@PathVariable Long todoId) {
        TodoDeleteResponseDto responseDto = todoService.deleteToService(todoId);
        if (responseDto.getStatus() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }


    /**
     * 대쉬보드 전체 수 조회
     */
    @GetMapping("/allCount")
    public ResponseEntity<String> getTodoAllCountAPI(){
        Long todoAllCountAPI = todoService.getTodoAllCountAPI();
        return ResponseEntity.ok("전체 테스크 수 : " + todoAllCountAPI);
    }

    /**
     * 대쉬보드 특정 상태 개수 조회
     * @param progress
     * @return
     */
    @GetMapping("/progress/{progress}")
    public ResponseEntity <String> getProgressCount(@PathVariable String progress){
        Long progressCount = todoService.getProgressCount(progress);
        return ResponseEntity.ok(progress.toUpperCase() + " 테스크 수는 :" + progressCount);
    }

    /**
     * 대시보드 완료율 구하기
     */
    @GetMapping("/donePercent")
    public ResponseEntity<String> getCompletedPercent(){
        double progressPercent = todoService.getProgressPercent();
        return ResponseEntity.ok("완료율은 다음과 같습니다. : " + progressPercent+"%");
    }

}

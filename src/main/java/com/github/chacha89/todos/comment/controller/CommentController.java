package com.github.chacha89.todos.comment.controller;

import com.github.chacha89.todos.comment.dto.*;
import com.github.chacha89.todos.comment.service.CommentService;
import com.github.chacha89.todos.jwt.service.JWTService;
import io.jsonwebtoken.Claims;
import com.github.chacha89.todos.todo.entity.Progress;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.chacha89.todos.user.dto.responseDto.APIResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private final JWTService jwtService;

    public CommentController(CommentService commentService, JWTService jwtService) {
        this.commentService = commentService;
        this.jwtService = jwtService;
    }

    /**
     * 커멘트 생성 API
     */
    @PostMapping
    public ResponseEntity<CommentCreateResponseDto> createCommentAPI(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody CommentCreateRequestDto requestDto
    ) {
        String token = bearerToken.replace("Bearer ", "").trim();
        Claims claims = jwtService.verifyToken(token);
        Long userId = Long.parseLong(claims.getSubject());
        CommentCreateResponseDto responseDto = commentService.createCommentAPI(userId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * 커멘트 삭제 API
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDeleteResponseDto> deleteCommentAPI(@PathVariable Long id) {
        CommentDeleteResponseDto responseDto = commentService.deleteCommentService(id);
        if (responseDto.getStatus() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }


    /**
     * 댓글 수정
     */
    @PatchMapping("/{id}")
    public APIResponse <CommentData> updateCommentAPI(@PathVariable Long id,
                                 @RequestBody CommentCreateRequestDto updateRequest){
        CommentData commentData = commentService.updateCommentAPI(id, updateRequest);

        return APIResponse.success(commentData, "댓글 수정이 완료되었습니다.");
    }

    /**
     * 댓글 전체 조회
     * @param progress todo, inProgress, done, overdue
     * @param comment 댓글내용
     * @param page 0
     * @param size 10
     * @return
     * @RequestParam Progress(Enum) progress 으로 매개변수 설정 후, 포스트맨에서 Param key : value 입력시에 에러가 발생하는 현상 확인.
     * Progress(Enum) 내부에서 String을 받으면 Enum으로 변환시켜주는 메서드 구현하여 @RequestParam String progress 받을 수 있게 함.
     */

    @GetMapping()
    public APIResponse<CommentListPaginatedResponseDto<CommentListResponseDto>> getCommentAPI (@RequestParam String progress,
                                                                    @RequestParam String comment,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        Progress FromStringToEnum = Progress.fromString(progress);
        CommentListPaginatedResponseDto<CommentListResponseDto> commentListService
                = commentService.getCommentListService(FromStringToEnum, comment, page, size);
        APIResponse<CommentListPaginatedResponseDto<CommentListResponseDto>> success
                = APIResponse.success(commentListService);
        return success;

    }

}

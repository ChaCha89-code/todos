package com.github.chacha89.todos.comment.controller;

import com.github.chacha89.todos.comment.dto.CommentCreateRequestDto;
import com.github.chacha89.todos.comment.dto.CommentCreateResponseDto;
import com.github.chacha89.todos.comment.dto.CommentDeleteResponseDto;
import com.github.chacha89.todos.comment.dto.CommentData;
import com.github.chacha89.todos.comment.service.CommentService;
import com.github.chacha89.todos.jwt.service.JWTService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.chacha89.todos.user.dto.responseDto.APIResponse;
import org.springframework.web.bind.annotation.*;

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
     * @param commentId
     * @return
     */
    public ResponseEntity<CommentDeleteResponseDto> deleteCommentAPI(@PathVariable Long commentId) {
        CommentDeleteResponseDto responseDto = commentService.deleteCommentService(commentId);
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
}

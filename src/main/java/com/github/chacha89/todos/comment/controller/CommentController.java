package com.github.chacha89.todos.comment.controller;

import com.github.chacha89.todos.comment.dto.CommentCreateRequestDto;
import com.github.chacha89.todos.comment.dto.CommentData;
import com.github.chacha89.todos.comment.service.CommentService;
import com.github.chacha89.todos.user.dto.responseDto.APIResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 커멘트 생성 API
     */
    @PostMapping
    public void createCommentAPI(@RequestBody CommentCreateRequestDto requestDto) {
        // commentService.
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

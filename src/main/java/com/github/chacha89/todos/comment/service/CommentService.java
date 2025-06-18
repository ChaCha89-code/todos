package com.github.chacha89.todos.comment.service;

import com.github.chacha89.todos.comment.dto.CommentCreateRequestDto;
import com.github.chacha89.todos.comment.dto.CommentData;
import com.github.chacha89.todos.comment.entity.Comment;
import com.github.chacha89.todos.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * 댓글 수정
     */
    public CommentData updateCommentAPI(Long id, CommentCreateRequestDto updateRequest){
        Comment commentToUpdate = commentRepository.findById(id).orElseThrow();

        String originalComment = commentToUpdate.getComment();
        String newComment = updateRequest.getComment();

        if( !(newComment ==null) &&!newComment.equals(originalComment)){
            commentToUpdate.changeComment(newComment);
        }

        Comment updatedComment = commentRepository.save(commentToUpdate);

        CommentData commentDataResponse = new CommentData(updatedComment.getId(), updatedComment.getUser().getId(), updatedComment.getTodo().getId(), updatedComment.getComment(), updatedComment.getCreatedAt());
        return commentDataResponse ;
    }

}

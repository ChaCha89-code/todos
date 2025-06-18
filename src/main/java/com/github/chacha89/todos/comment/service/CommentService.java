package com.github.chacha89.todos.comment.service;

import com.github.chacha89.todos.comment.dto.CommentCreateRequestDto;
import com.github.chacha89.todos.comment.dto.CommentCreateResponseDto;
import com.github.chacha89.todos.comment.dto.CommentData;
import com.github.chacha89.todos.comment.dto.CommentDeleteResponseDto;
import com.github.chacha89.todos.comment.entity.Comment;
import com.github.chacha89.todos.comment.repository.CommentRepository;
import com.github.chacha89.todos.exception.CommentCreateException;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.todo.repository.TodoRepository;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentService(TodoRepository todoRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository)
    {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * 댓글 생성 기능
     */
    public CommentCreateResponseDto createCommentAPI(Long userId, CommentCreateRequestDto requestDto) {
        // 1. 데이터 준비_1
        Long todoId = requestDto.getTodoId();
        String comment = requestDto.getComment();

        // 2. 예회 처리
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new CommentCreateException(404, "회원 ID가 존재하지 않습니다."));
        Todo foundTodo = todoRepository.findById(todoId).orElseThrow(() -> new CommentCreateException(404, "할 일 ID가 존재하지 않습니다."));
        if(comment == null || comment.isEmpty()) {
            throw new CommentCreateException(400, "댓글 입력란이 비어있습니다.");
        }

        // 3. 엔티티 생성
        Comment newComment = new Comment(foundUser, foundTodo, comment);

        // 4. 저장
        Comment savedComment = commentRepository.save(newComment);

        // 5. 데이터 준비_2
        Long foundCommentId = savedComment.getId();
        Long foundUserId = savedComment.getUser().getId();
        Long foundTodoId = savedComment.getTodo().getId();
        String foundComment = savedComment.getComment();
        LocalDateTime foundCreatedAt = savedComment.getCreatedAt();
        LocalDateTime foundUpdatedAt = savedComment.getUpdatedAt();

        // 6. ResponseDto에 넣어줄 CommentData 준비
        CommentData newCommentData= new CommentData(foundCommentId, foundUserId, foundTodoId, foundComment, foundCreatedAt, foundUpdatedAt);

        // 7. 반환
        return new CommentCreateResponseDto(true, 200, newCommentData);
    }

    /**
     * 커멘트 삭제 기능
     * @param commentId
     * @return
     */
    public CommentDeleteResponseDto deleteCommentService(Long commentId) {
        // 데이터 준비
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();

            comment.setDeleted(true);
            comment.setDeletedAt(LocalDateTime.now());
            commentRepository.save(comment);

            CommentDeleteResponseDto responseDto = new CommentDeleteResponseDto(200, "댓글이 성공적으로 삭제되었습니다.");
            return responseDto;
        } else {
            CommentDeleteResponseDto responseDto = new CommentDeleteResponseDto(404, "댓글이 존재하지 않습니다.");
            return responseDto;
        }
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

        CommentData commentDataResponse = new CommentData(updatedComment.getId(), updatedComment.getUser().getId(), updatedComment.getTodo().getId(), updatedComment.getComment(), updatedComment.getCreatedAt(), updatedComment.getUpdatedAt());
        return commentDataResponse ;
    }

}

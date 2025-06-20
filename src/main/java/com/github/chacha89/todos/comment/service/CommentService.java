package com.github.chacha89.todos.comment.service;

import com.github.chacha89.todos.comment.dto.*;
import com.github.chacha89.todos.comment.entity.Comment;
import com.github.chacha89.todos.comment.repository.CommentRepository;
import com.github.chacha89.todos.comment.exception.CommentCreateException;
import com.github.chacha89.todos.common.commonEnum.Progress;
import com.github.chacha89.todos.todo.entity.Todo;
import com.github.chacha89.todos.todo.repository.TodoRepository;
import com.github.chacha89.todos.user.entity.User;
import com.github.chacha89.todos.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
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
        Long foundCommentId = savedComment.getId();  log.info("foundCommentId: {}",foundCommentId);
        Long foundUserId = savedComment.getUser().getId(); log.info("foundUserId: {}",foundUserId);
        Long foundTodoId = savedComment.getTodo().getId(); log.info("foundTodoId: {}",foundTodoId);
        String foundComment = savedComment.getComment(); log.info("foundComment: {}",foundComment);
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
    public CommentData updateCommentAPI(Long id, CommentCreateRequestDto updateRequest) {
        Comment commentToUpdate = commentRepository.findById(id).orElseThrow();

        String originalComment = commentToUpdate.getComment();
        String newComment = updateRequest.getComment();

        if (!(newComment == null) && !newComment.equals(originalComment)) {
            commentToUpdate.changeComment(newComment);
        }

        Comment updatedComment = commentRepository.save(commentToUpdate);

        CommentData commentDataResponse = new CommentData(updatedComment.getId(), updatedComment.getUser().getId(), updatedComment.getTodo().getId(), updatedComment.getComment(), updatedComment.getCreatedAt(), updatedComment.getUpdatedAt());
        return commentDataResponse ;
    }

    // 테스크(TODO, INPROGRESS, DOEN, OVERDUE)별 댓글 전체 조회
    public CommentListPaginatedResponseDto<CommentListResponseDto> getCommentListService( Progress progress,
                                                               String comment,
                                                              int page,
                                                               int size) {

        // 페이지네이션을 위한 준비
        Pageable pageable = PageRequest.of(page, size);
        //데이터 조회
        Page<Comment> commentListFromTodo
                = commentRepository.findCommentsByProgressAndContent(progress,comment,pageable);
        //조회한 데이터에서 코멘트내용 GET
        // STREAM으로 ENTITY COMMENT와 응답 DTO 매핑 시켜서 LIST 진행
        List<CommentListResponseDto> listResponseDto = commentListFromTodo.getContent()
                .stream()
                .map(Comment -> new CommentListResponseDto(Comment)).toList();
        // 반환 준비
        // 페이지네이션 DTO에 리스트된 내용, 전체 페이지 데이터의 수, 전체 페이지 수, 현재 페이지 반환
        CommentListPaginatedResponseDto<CommentListResponseDto> pagingCommentListResponseDto
                = new CommentListPaginatedResponseDto<>(listResponseDto, commentListFromTodo.getTotalElements()
                , commentListFromTodo.getTotalPages(), page);
        // 반환
        return pagingCommentListResponseDto;


    }

}

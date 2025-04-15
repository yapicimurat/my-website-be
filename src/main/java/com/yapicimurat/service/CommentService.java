package com.yapicimurat.service;

import com.yapicimurat.dto.comment.CommentDTO;
import com.yapicimurat.dto.comment.CommentInputDTO;
import com.yapicimurat.dto.pageable.PageableDTO;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.projection.CommentSummaryDTO;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {
    CommentDTO getById(UUID id) throws EntityNotFoundException;
    Optional<CommentDTO> getByIdOptional(UUID id);
    PageableDTO<CommentSummaryDTO> getAllByArticle(UUID articleId, Integer currentPage);
    PageableDTO<CommentSummaryDTO> getCommentAnswers(UUID parentCommentId, Integer currentPage);
    CommentDTO makeComment(UUID articleId, CommentInputDTO commentInputDTO);
    String answerToComment(UUID id, CommentInputDTO commentInputDTO);
    CommentDTO publishCommentById(UUID id);
    CommentDTO banCommentById(UUID id);
    CommentDTO updateById(UUID id, CommentInputDTO commentInputDTO);
}

package com.yapicimurat.service;

import com.yapicimurat.dto.comment.CommentDTO;
import com.yapicimurat.dto.comment.CommentInputDTO;
import com.yapicimurat.dto.comment.ParentCommentDTO;
import com.yapicimurat.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {
    CommentDTO getById(UUID id) throws EntityNotFoundException;
    Optional<CommentDTO> getByIdOptional(UUID id);
    List<CommentDTO> getAllByArticle(UUID articleId);
    List<CommentDTO> getCommentAnswers(UUID parentCommentId);
    CommentDTO makeComment(UUID articleId, CommentInputDTO commentInputDTO);
    String answerToComment(UUID id, CommentInputDTO commentInputDTO);
    CommentDTO publishCommentById(UUID id);
    CommentDTO banCommentById(UUID id);
    CommentDTO updateById(UUID id, CommentInputDTO commentInputDTO);
}

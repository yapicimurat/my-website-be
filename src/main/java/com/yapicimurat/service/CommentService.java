package com.yapicimurat.service;

import com.yapicimurat.web.input.CommentCreateRequest;
import com.yapicimurat.web.input.CommentUpdateRequest;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentService {
    Comment getById(UUID id) throws EntityNotFoundException;
    Optional<Comment> getByIdOptional(UUID id);
    List<Comment> getAllByArticle(UUID articleId);
    Comment makeComment(UUID articleId, CommentCreateRequest requestBody);
    Comment answerToComment(UUID commentId, CommentCreateRequest commentCreateRequest);
    UUID publishCommentById(UUID id);
    UUID banCommentById(UUID id);
    Comment updateById(UUID id, CommentUpdateRequest requestBody);
}

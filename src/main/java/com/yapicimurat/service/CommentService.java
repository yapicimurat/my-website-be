package com.yapicimurat.service;

import com.yapicimurat.controller.request.CommentCreateRequest;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Comment;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface CommentService {
    Comment getById(UUID id) throws EntityNotFoundException;
    Optional<Comment> getByIdOptional(UUID id);
    Comment addComment(UUID articleId, CommentCreateRequest requestBody);
    Comment addAnswerToParentComment(UUID articleId, CommentCreateRequest requestBody);
}

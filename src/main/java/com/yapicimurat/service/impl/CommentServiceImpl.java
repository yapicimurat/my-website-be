package com.yapicimurat.service.impl;

import com.yapicimurat.controller.request.CommentCreateRequest;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Article;
import com.yapicimurat.model.Comment;
import com.yapicimurat.repository.CommentRepository;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.service.CommentService;

import java.util.Optional;
import java.util.UUID;

public class CommentServiceImpl implements CommentService {
    private final ArticleService articleService;
    private final CommentRepository commentRepository;
    public CommentServiceImpl(CommentRepository commentRepository, ArticleService articleService){

        this.commentRepository = commentRepository;
        this.articleService = articleService;
    }

    public Comment getById(UUID id) throws EntityNotFoundException {
        return commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<Comment> getByIdOptional(UUID id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment addComment(UUID articleId, CommentCreateRequest requestBody) {
        final Article article = articleService.getById(articleId);
        final Comment comment = new Comment();

        comment.setName(requestBody.getName());
        comment.setLastName(requestBody.getLastName());
        comment.setEmail(requestBody.getEmail());
        comment.setIsAnswer(false);
        comment.setArticle(article);
        comment.setVisible(false);

        return commentRepository.save(comment);
    }

    @Override
    public Comment addAnswerToParentComment(UUID articleId, CommentCreateRequest requestBody) {
        final Comment parentComment = getById(UUID.fromString(requestBody.getParentCommentId()));
        final Comment answer = new Comment();

        answer.setName(requestBody.getName());
        answer.setLastName(requestBody.getLastName());
        answer.setEmail(requestBody.getEmail());
        answer.setIsAnswer(true);
        answer.setArticle(parentComment.getArticle());
        answer.setParentComment(parentComment);
        answer.setVisible(false);

        return commentRepository.save(answer);
    }
}

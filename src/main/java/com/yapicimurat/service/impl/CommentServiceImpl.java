package com.yapicimurat.service.impl;

import com.yapicimurat.controller.request.CommentCreateRequest;
import com.yapicimurat.controller.request.CommentUpdateRequest;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Article;
import com.yapicimurat.model.Comment;
import com.yapicimurat.repository.CommentRepository;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
    public List<Comment> getByArticleId(UUID articleId) {
        Article article = articleService.getById(articleId);

        return commentRepository.getByArticle(article);
    }

    @Override
    public Comment makeComment(UUID articleId, CommentCreateRequest requestBody) {
        final Article article = articleService.getById(articleId);
        final Comment comment = new Comment();

        comment.setName(requestBody.getName());
        comment.setLastName(requestBody.getLastName());
        comment.setEmail(requestBody.getEmail());
        comment.setText(requestBody.getText());
        comment.setIsAnswer(false);
        comment.setArticle(article);
        comment.setVisible(false);

        return commentRepository.save(comment);
    }

    @Override
    public Comment answerToComment(UUID commentId, CommentCreateRequest commentCreateRequest) {
        final Comment parentComment = getById(commentId);
        final Comment answer = new Comment();

        answer.setName(commentCreateRequest.getName());
        answer.setLastName(commentCreateRequest.getLastName());
        answer.setEmail(commentCreateRequest.getEmail());
        answer.setText(commentCreateRequest.getText());
        answer.setIsAnswer(true);
        answer.setParentComment(parentComment);
        answer.setArticle(parentComment.getArticle());

        return commentRepository.save(answer);
    }

    @Override
    public UUID publishCommentById(UUID id) {
        final Comment comment = getById(id);

        comment.setVisible(true);

        return commentRepository.save(comment).id;
    }

    @Override
    public UUID banCommentById(UUID id) {
        final Comment comment = getById(id);

        comment.setVisible(false);

        return commentRepository.save(comment).id;
    }

    @Override
    public Comment updateById(UUID id, CommentUpdateRequest requestBody) {
        final Comment comment = getById(id);

        comment.setName(requestBody.getName());
        comment.setLastName(requestBody.getLastName());
        comment.setEmail(requestBody.getEmail());
        comment.setText(requestBody.getText());

        return commentRepository.save(comment);
    }
}

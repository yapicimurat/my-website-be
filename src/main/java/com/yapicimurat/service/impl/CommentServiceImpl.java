package com.yapicimurat.service.impl;

import com.yapicimurat.common.mapper.ArticleMapper;
import com.yapicimurat.common.mapper.CommentMapper;
import com.yapicimurat.dto.comment.CommentDTO;
import com.yapicimurat.dto.comment.CommentInputDTO;
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
    public CommentServiceImpl(CommentRepository commentRepository, ArticleService articleService) {
        this.commentRepository = commentRepository;
        this.articleService = articleService;
    }

    public CommentDTO getById(UUID id) throws EntityNotFoundException {
        return CommentMapper.INSTANCE.convertCommentEntityToCommentDTO(findById(id));
    }

    @Override
    public Optional<CommentDTO> getByIdOptional(UUID id) {
        return commentRepository.findById(id)
                .map(CommentMapper.INSTANCE::convertCommentEntityToCommentDTO);
    }

    @Override
    public List<CommentDTO> getAllByArticle(UUID articleId) {
        return CommentMapper.INSTANCE
                .convertCommentEntityListToCommentDTOList(
                        commentRepository.getAllByArticleOrderByCreatedAtDesc(articleId)
                );
    }

    @Override
    public List<CommentDTO> getCommentAnswers(UUID parentCommentId) {
        return CommentMapper.INSTANCE
                .convertCommentEntityListToCommentDTOList(
                        commentRepository.getCommentAnswersByParentCommentIdOrderByCreatedAtDesc(parentCommentId)
                );
    }

    @Override
    public CommentDTO makeComment(UUID articleId, CommentInputDTO commentInputDTO) {
        final Article article = ArticleMapper.INSTANCE.convertArticleDTOToArticleEntity(articleService.getById(articleId));
        final Comment comment = CommentMapper.INSTANCE.convertCommentInputDTOToCommentEntity(commentInputDTO);
        comment.setVisible(false);
        comment.setArticle(article);

        return CommentMapper.INSTANCE.convertCommentEntityToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public String answerToComment(UUID parentCommentId, CommentInputDTO commentInputDTO) {
        final Comment parentComment = findById(parentCommentId);
        final Comment answerComment = CommentMapper.INSTANCE.convertCommentInputDTOToCommentEntity(commentInputDTO);

        answerComment.setAnswer(true);
        answerComment.setParentComment(parentComment);
        answerComment.setArticle(parentComment.getArticle());
        answerComment.setVisible(false);

        return commentRepository.save(answerComment).id.toString();
    }

    @Override
    public CommentDTO publishCommentById(UUID id) {
        final Comment comment = findById(id);
        comment.setVisible(true);
        return CommentMapper.INSTANCE.convertCommentEntityToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public CommentDTO banCommentById(UUID id) {
        final Comment comment = findById(id);
        comment.setVisible(false);
        return CommentMapper.INSTANCE.convertCommentEntityToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public CommentDTO updateById(UUID id, CommentInputDTO commentInputDTO) {
        checkCommentIsExistById(id);
        final Comment commentToSave = CommentMapper.INSTANCE.convertCommentInputDTOToCommentEntity(commentInputDTO);
        return CommentMapper.INSTANCE.convertCommentEntityToCommentDTO(commentRepository.save(commentToSave));
    }

    private Comment findById(UUID id) {
        return commentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    private void checkCommentIsExistById(UUID id) {
        if(!commentRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
    }
}

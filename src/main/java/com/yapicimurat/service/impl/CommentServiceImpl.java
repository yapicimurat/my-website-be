package com.yapicimurat.service.impl;

import com.yapicimurat.common.mapper.ArticleMapper;
import com.yapicimurat.common.mapper.CommentMapper;
import com.yapicimurat.dto.comment.CommentDTO;
import com.yapicimurat.dto.comment.CommentInputDTO;
import com.yapicimurat.dto.pageable.PageableDTO;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Article;
import com.yapicimurat.model.Comment;
import com.yapicimurat.model.projection.CommentAnswerCountDTO;
import com.yapicimurat.model.projection.CommentSummaryDTO;
import com.yapicimurat.repository.CommentRepository;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.service.CommentService;
import com.yapicimurat.util.CommonUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private static final int TOTAL_COMMENTS_IN_A_PAGE = 1;
    private final ArticleService articleService;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository, @Lazy ArticleService articleService) {
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
    public PageableDTO<CommentSummaryDTO> getAllByArticle(UUID articleId, Integer currentPage) {
        currentPage = CommonUtil.clampDataPageNumber(currentPage - 1);
        Pageable page = PageRequest.of(currentPage, TOTAL_COMMENTS_IN_A_PAGE);
        Page<Comment> comments = commentRepository.getAllCommentsByArticleIdDesc(page, articleId);
        List<CommentSummaryDTO> commentSummaryDTOList = createCommentSummaryDTOList(comments.getContent(), Strings.EMPTY);
        return new PageableDTO<>(
                commentSummaryDTOList,
                comments.getTotalPages(),
                TOTAL_COMMENTS_IN_A_PAGE,
                currentPage,
                comments.hasNext(),
                comments.hasPrevious()
        );
    }

    private List<CommentSummaryDTO> createCommentSummaryDTOList(List<Comment> comments, String parentCommentId) {
        if(Objects.nonNull(comments)) {
            Set<UUID> commentIdSet = comments.stream().map(Comment::getId).collect(Collectors.toSet());
            List<CommentAnswerCountDTO> commentAnswerCountDTOList = commentRepository.getAnswerCommentAmountsOfParentComments(commentIdSet);
            Map<UUID, Integer> commentsCountMappedByCommentId = commentAnswerCountDTOList.stream()
                    .collect(Collectors.toMap(
                            CommentAnswerCountDTO::id,
                            CommentAnswerCountDTO::count));
            return comments.stream().map(comment -> {
                Integer amountOfComment = commentsCountMappedByCommentId.get(comment.getId());
                return new CommentSummaryDTO(
                  comment.getId().toString(),
                  comment.getName(),
                        comment.getLastName(),
                        comment.getEmail(),
                        comment.getText(),
                        comment.getAnswer(),
                        Objects.nonNull(amountOfComment) ? amountOfComment : 0,
                        comment.getCreatedAt(),
                        parentCommentId
                );
            })
            .toList();
        }
        return null;
    }

    @Override
    public PageableDTO<CommentSummaryDTO> getCommentAnswers(UUID parentCommentId, Integer currentPage) {
        currentPage = CommonUtil.clampDataPageNumber(currentPage - 1);
        Pageable page = PageRequest.of(currentPage, TOTAL_COMMENTS_IN_A_PAGE);
        Page<Comment> answers = commentRepository.getAnswerCommentsByParentCommentId(page, parentCommentId);
        List<CommentSummaryDTO> commentSummaryDTOList = createCommentSummaryDTOList(answers.getContent(), String.valueOf(parentCommentId));
        return new PageableDTO<>(
                commentSummaryDTOList,
                answers.getTotalPages(),
                TOTAL_COMMENTS_IN_A_PAGE,
                currentPage,
                answers.hasNext(),
                answers.hasPrevious()
        );
    }

    @Override
    public CommentDTO makeComment(UUID articleId, CommentInputDTO commentInputDTO) {
        final Article article = ArticleMapper.INSTANCE.toArticleEntity(articleService.getById(articleId));
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

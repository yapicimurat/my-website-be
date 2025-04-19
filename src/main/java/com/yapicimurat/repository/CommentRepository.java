package com.yapicimurat.repository;

import com.yapicimurat.model.Comment;
import com.yapicimurat.model.projection.CommentAnswerCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<Comment, UUID> {
    @Query(
            value = "SELECT c FROM Comment c WHERE c.article.id = :articleId AND c.isAnswer = FALSE ORDER BY c.createdAt DESC",
            countQuery = "SELECT COUNT(c.id) FROM Comment c WHERE c.article.id = :articleId AND  c.isAnswer = FALSE"
    )
    Page<Comment> getAllCommentsByArticleIdDesc(Pageable pageable, @Param("articleId") UUID articleId);

    @Query(
            value = "SELECT c FROM Comment c LEFT JOIN FETCH c.parentComment LEFT JOIN FETCH c.rootComment WHERE c.rootComment.id = :rootCommentId ORDER BY c.createdAt DESC",
            countQuery = "SELECT COUNT(c.id) FROM Comment c WHERE c.rootComment.id = :rootCommentId"
    )
    Page<Comment> getAnswerCommentsByRootCommentId(Pageable pageable, @Param("rootCommentId") UUID rootCommentId);

    @Query("SELECT new com.yapicimurat.model.projection.CommentAnswerCountDTO(c.id, c.parentComment.id, c.rootComment.id, size(c.answers)) FROM Comment c WHERE c.id IN :parentCommentIds")
    List<CommentAnswerCountDTO> getAnswerCommentAmountsOfParentComments(@Param("parentCommentIds") Set<UUID> parentCommentIds);
}
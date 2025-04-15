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

    @Query("SELECT DISTINCT c FROM Comment c LEFT JOIN FETCH c.answers WHERE c.isAnswer = false AND c.isDeleted = false AND c.visible = true AND c.article.id = :articleId ORDER BY c.createdAt DESC")
    List<Comment> getAllByArticleOrderByCreatedAtDesc(@Param("articleId") UUID articleId);

    @Query("SELECT DISTINCT c FROM Comment c LEFT JOIN FETCH c.answers WHERE c.isAnswer = true AND c.visible = true AND c.isDeleted = false AND c.parentComment.id = :parentCommentId")
    List<Comment> getCommentAnswersByParentCommentIdOrderByCreatedAtDesc(@Param("parentCommentId") UUID parentCommentId);

    @Query(
            value = "SELECT c FROM Comment c WHERE c.article.id = :articleId AND c.isAnswer = FALSE ORDER BY c.createdAt DESC",
            countQuery = "SELECT COUNT(c.id) FROM Comment c WHERE c.article.id = :articleId AND  c.isAnswer = FALSE"
    )
    Page<Comment> getAllCommentsByArticleIdDesc(Pageable pageable, @Param("articleId") UUID articleId);

    @Query(
            value = "SELECT c FROM Comment c WHERE c.parentComment.id = :parentCommentId ORDER BY c.createdAt DESC",
            countQuery = "SELECT COUNT(c.id) FROM Comment c WHERE c.parentComment.id = :parentCommentId"
    )
    Page<Comment> getAnswerCommentsByParentCommentId(Pageable pageable, @Param("parentCommentId") UUID parentCommentId);

    @Query("SELECT new com.yapicimurat.model.projection.CommentAnswerCountDTO(c.id, size(c.answers)) FROM Comment c WHERE c.id IN :parentCommentIds")
    List<CommentAnswerCountDTO> getAnswerCommentAmountsOfParentComments(@Param("parentCommentIds") Set<UUID> parentCommentIds);
}
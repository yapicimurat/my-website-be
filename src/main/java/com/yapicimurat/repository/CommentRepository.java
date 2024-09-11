package com.yapicimurat.repository;

import com.yapicimurat.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    @Query("SELECT DISTINCT c FROM Comment c LEFT JOIN FETCH c.answers WHERE c.isAnswer = false AND c.isDeleted = false AND c.visible = true AND c.article.id = :articleId ORDER BY c.createdAt DESC")
    List<Comment> getAllByArticleOrderByCreatedAtDesc(@Param("articleId") UUID articleId);

    @Query("SELECT DISTINCT c FROM Comment c LEFT JOIN FETCH c.answers WHERE c.isAnswer = true AND c.visible = true AND c.isDeleted = false AND c.parentComment.id = :parentCommentId")
    List<Comment> getCommentAnswersByParentCommentIdOrderByCreatedAtDesc(@Param("parentCommentId") UUID parentCommentId);
}
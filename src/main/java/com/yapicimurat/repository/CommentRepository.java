package com.yapicimurat.repository;

import com.yapicimurat.model.Article;
import com.yapicimurat.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query(value = "SELECT\n" +
            "*\n" +
            "FROM\n" +
            "comment as c\n" +
            "LEFT JOIN comment as a ON c.id = a.parent_comment_id\n" +
            "WHERE c.is_answer = 0 ORDER BY c.created_at DESC;", nativeQuery = true)
    List<Comment> getAllByArticleOrderByCreatedAtDesc(Article article);

}
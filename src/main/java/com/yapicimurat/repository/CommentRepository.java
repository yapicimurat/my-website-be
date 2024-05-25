package com.yapicimurat.repository;

import com.yapicimurat.model.Article;
import com.yapicimurat.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> getByArticle(Article article);
}
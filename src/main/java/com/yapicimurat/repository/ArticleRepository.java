package com.yapicimurat.repository;

import com.yapicimurat.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ArticleRepository extends PagingAndSortingRepository<Article, UUID> {
    @Query("SELECT " +
            "CASE " +
            "   WHEN COUNT(article.title) = 1 THEN TRUE" +
            "   ELSE FALSE " +
            "END FROM Article as article WHERE article.title = LOWER(:title)")
    boolean isTitleUsedByAnother(String title);

    @Query("SELECT " +
            "CASE " +
            "   WHEN COUNT(article.title) = 1 THEN TRUE" +
            "   ELSE FALSE " +
            "END FROM Article as article WHERE article.id != :id AND article.title = LOWER(:title)")
    boolean isTitleUsedByAnotherWithId(UUID id, String title);
}

package com.yapicimurat.repository;

import com.yapicimurat.model.Article;
import com.yapicimurat.model.projection.ArticleCommentCountDTO;
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
public interface ArticleRepository extends PagingAndSortingRepository<Article, UUID> {
    @Query("SELECT " +
            "CASE " +
            "   WHEN COUNT(article.title) = 1 THEN TRUE" +
            "   ELSE FALSE " +
            "END FROM Article as article WHERE article.title = LOWER(:title)")
    boolean checkIsTitleAlreadyExists(String title);

    @Query("SELECT " +
            "CASE " +
            "   WHEN COUNT(article.title) = 1 THEN TRUE" +
            "   ELSE FALSE " +
            "END FROM Article as article WHERE article.id != :id AND article.title = LOWER(:title)")
    boolean checkIsTitleAlreadyExistsByExceptId(UUID id, String title);

    @Query(
            value = "SELECT a FROM Article a ORDER BY a.createdAt DESC",
            countQuery = "SELECT COUNT(a.id) FROM Article a"
    )
    Page<Article> getAllArticles(Pageable pageable);

    @Query("""
        SELECT new com.yapicimurat.model.projection.ArticleCommentCountDTO(a.id, COUNT(c))
        FROM Comment c
        JOIN c.article a
        WHERE a.id IN :articleIds
        GROUP BY a.id
    """)
    List<ArticleCommentCountDTO> findCommentCountsByArticleIds(@Param("articleIds") Set<UUID> articleIds);

    @Query("SELECT a FROM Article a JOIN FETCH a.categories WHERE a.id IN :articleIds")
    List<Article> loadArticleCategories(@Param("articleIds") Set<UUID> articleIds);

}

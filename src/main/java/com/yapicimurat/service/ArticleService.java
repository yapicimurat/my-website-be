package com.yapicimurat.service;

import com.yapicimurat.dto.article.ArticleDTO;
import com.yapicimurat.dto.article.ArticleInputDTO;
import com.yapicimurat.dto.pageable.PageableDTO;
import com.yapicimurat.model.projection.ArticleDetailDTO;
import com.yapicimurat.model.projection.ArticleSummaryDTO;
import java.util.Optional;
import java.util.UUID;

public interface ArticleService {
    PageableDTO<ArticleSummaryDTO> getAll(Integer currentPage);
    Optional<ArticleDTO> getOptionalById(UUID id);
    ArticleDTO getById(UUID id);
    ArticleDTO create(ArticleInputDTO articleInput);
    ArticleDTO update(String id, ArticleInputDTO article);
    boolean existsById(UUID id);
    ArticleDetailDTO getArticleDetailById(UUID id);
}

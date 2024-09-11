package com.yapicimurat.service;

import com.yapicimurat.dto.article.ArticleDTO;
import com.yapicimurat.dto.article.ArticleInputDTO;
import com.yapicimurat.dto.pageable.PageableDTO;

import java.util.Optional;
import java.util.UUID;

public interface ArticleService {
    PageableDTO<ArticleDTO> getAll(Integer currentPage);
    Optional<ArticleDTO> getOptionalArticleDTOById(UUID id);
    ArticleDTO getById(UUID id);
    ArticleDTO create(ArticleInputDTO articleInput);
    ArticleDTO update(String id, ArticleInputDTO article);
    boolean existsById(UUID id);
}

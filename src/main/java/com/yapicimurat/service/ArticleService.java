package com.yapicimurat.service;

import com.yapicimurat.controller.request.ArticleCreateRequest;
import com.yapicimurat.controller.request.ArticleUpdateRequest;
import com.yapicimurat.dto.Pageable;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.model.Article;
import java.util.Optional;
import java.util.UUID;

public interface ArticleService {
    Pageable<Article> getAll(Integer currentPage);
    Boolean isTitleUsedByAnotherArticle(String title) throws EntityAlreadyExistsException;
    Boolean isTitleUsedByAnotherArticleWithId(UUID id, String title) throws EntityAlreadyExistsException;
    Optional<Article> getByIdOptional(UUID id);
    Article getById(UUID id);
    String create(ArticleCreateRequest requestBody);
    Article update(String id, ArticleUpdateRequest requestBody);
}

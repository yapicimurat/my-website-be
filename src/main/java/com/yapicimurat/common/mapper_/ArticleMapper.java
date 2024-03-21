package com.yapicimurat.common.mapper_;

import com.yapicimurat.controller.request.ArticleCreateRequest;
import com.yapicimurat.controller.request.ArticleUpdateRequest;
import com.yapicimurat.dto.ArticleDTO;
import com.yapicimurat.model.Article;
import org.mapstruct.factory.Mappers;

public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    ArticleDTO convertArticleToArticleDTO(Article source);
    Article convertArticleCreateRequestToArticle(ArticleCreateRequest articleCreateRequest);
    Article convertArticleUpdateRequestToArticle(ArticleUpdateRequest articleUpdateRequest);
}

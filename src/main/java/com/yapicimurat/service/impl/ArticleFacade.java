package com.yapicimurat.service.impl;

import com.yapicimurat.controller.request.ArticleCreateRequest;
import com.yapicimurat.controller.request.ArticleUpdateRequest;
import com.yapicimurat.dto.ArticleDTO;
import com.yapicimurat.model.Article;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.util.GeneralUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleFacade {
    private final ArticleService articleService;
    private final ModelMapper modelMapper;
    public ArticleFacade(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    public List<ArticleDTO> getAll() {
        final List<Article> articleList = articleService.getAll();
        final List<ArticleDTO> articleDTOList = GeneralUtil
                .mapEntityListToDTOList(articleList, ArticleDTO.class, modelMapper);

        return articleDTOList;
    }

    public ArticleDTO getById(String id) {
        final Article article = articleService.getById(UUID.fromString(id));
        final ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return articleDTO;
    }

    public String create(ArticleCreateRequest requestBody) {
        final String articleId = articleService.create(requestBody);

        return articleId;
    }

    public ArticleDTO updateById(String id, ArticleUpdateRequest requestBody) {
        final Article article = articleService.update(id, requestBody);
        final ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return articleDTO;
    }

}

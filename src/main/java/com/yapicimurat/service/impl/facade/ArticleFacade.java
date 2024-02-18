package com.yapicimurat.service.impl.facade;

import com.yapicimurat.controller.request.ArticleCreateRequest;
import com.yapicimurat.controller.request.ArticleUpdateRequest;
import com.yapicimurat.controller.response.DataResponse;
import com.yapicimurat.controller.response.SuccessDataResponse;
import com.yapicimurat.dto.ArticleDTO;
import com.yapicimurat.dto.Pageable;
import com.yapicimurat.dto.PageableDTO;
import com.yapicimurat.model.Article;
import com.yapicimurat.service.ArticleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArticleFacade {
    private final ArticleService articleService;
    private final ModelMapper modelMapper;
    public ArticleFacade(ArticleService articleService, ModelMapper modelMapper) {
        this.articleService = articleService;
        this.modelMapper = modelMapper;
    }

    public DataResponse<PageableDTO<ArticleDTO>> getAll(Integer currentPage) {
        Pageable<Article> allArticlePageable = articleService.getAll(currentPage);

        return new SuccessDataResponse<>(modelMapper.map(allArticlePageable, PageableDTO.class), "");
    }

    public DataResponse<ArticleDTO> getById(String id) {
        final Article article = articleService.getById(UUID.fromString(id));
        final ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return new SuccessDataResponse<>(articleDTO, "");
    }

    public DataResponse<String> create(ArticleCreateRequest requestBody) {
        final String articleId = articleService.create(requestBody);

        return new SuccessDataResponse<>(articleId, "");
    }

    public DataResponse<ArticleDTO> updateById(String id, ArticleUpdateRequest requestBody) {
        final Article article = articleService.update(id, requestBody);
        final ArticleDTO articleDTO = modelMapper.map(article, ArticleDTO.class);

        return new SuccessDataResponse<>(articleDTO, "");
    }

}

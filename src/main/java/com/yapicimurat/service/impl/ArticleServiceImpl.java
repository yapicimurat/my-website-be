package com.yapicimurat.service.impl;

import com.yapicimurat.common.mapper.ArticleMapper;
import com.yapicimurat.common.mapper.CategoryMapper;
import com.yapicimurat.common.mapper.PageableMapper;
import com.yapicimurat.dto.article.ArticleDTO;
import com.yapicimurat.dto.article.ArticleInputDTO;
import com.yapicimurat.dto.pageable.PageableDTO;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Article;
import com.yapicimurat.model.Category;
import com.yapicimurat.repository.ArticleRepository;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final CategoryService categoryService;

    private final ArticleRepository articleRepository;

    private final int TOTAL_PER_PAGE = 4;

    public ArticleServiceImpl(CategoryService categoryService,
                              ArticleRepository articleRepository) {
        this.categoryService = categoryService;
        this.articleRepository = articleRepository;
    }

    @Override
    public PageableDTO<ArticleDTO> getAll(Integer currentPage) {
        currentPage = !Objects.isNull(currentPage) ? currentPage : 1;

        Pageable page = PageRequest.of(currentPage - 1, TOTAL_PER_PAGE);
        Page<Article> allArticlesPage = articleRepository.getAllArticles(page);

        List<ArticleDTO> articleDTOList = ArticleMapper.INSTANCE
                .convertArticleEntityListToArticleDTOList(allArticlesPage.stream().toList());

        return new PageableDTO<>(
                articleDTOList,
                allArticlesPage.getTotalPages(),
                TOTAL_PER_PAGE,
                currentPage,
                allArticlesPage.hasNext(),
                allArticlesPage.hasPrevious()
        );
    }

    @Override
    public Optional<ArticleDTO> getOptionalArticleDTOById(UUID id) {
        return articleRepository.findById(id).map(ArticleMapper.INSTANCE::convertArticleEntityToArticleDTO);
    }

    @Override
    public ArticleDTO getById(UUID id) {
        return articleRepository.findById(id).map(ArticleMapper.INSTANCE::convertArticleEntityToArticleDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ArticleDTO create(ArticleInputDTO articleInputDTO) {
        return saveCategory(null, articleInputDTO);
    }

    @Override
    public ArticleDTO update(String id, ArticleInputDTO articleInput) {
        return saveCategory(UUID.fromString(id), articleInput);
    }

    @Override
    public boolean existsById(UUID id) {
        return articleRepository.existsById(id);
    }

    private ArticleDTO saveCategory(@Nullable final UUID id, ArticleInputDTO articleInputDTO) {
        performPreChecksToSaveArticle(id, articleInputDTO);
        Set<Category> categorySet = new HashSet<>(CategoryMapper.INSTANCE
                .convertCategoryDTOListToCategoryEntityList(categoryService.getAllByIdIn(articleInputDTO.categoryIdSet())));
        Article articleToSave = ArticleMapper.INSTANCE
                .convertArticleInputDTOToArticleEntity(articleInputDTO, categorySet);
        articleToSave.setId(id);

        return ArticleMapper.INSTANCE.convertArticleEntityToArticleDTO(articleRepository.save(articleToSave));
    }

    private void performPreChecksToSaveArticle(@Nullable final UUID id, final ArticleInputDTO articleInputDTO) {
        if(Objects.nonNull(id)) {
            if(!articleRepository.existsById(id)) {
                throw new EntityNotFoundException();
            }
            checkIsTitleAlreadyExistsByExceptId(id, articleInputDTO.title());
        }else {
            checkIsTitleAlreadyExists(articleInputDTO.title());
        }
    }

    public void checkIsTitleAlreadyExists(String title) {
        if(articleRepository.checkIsTitleAlreadyExists(title)) {
            throw new EntityAlreadyExistsException();
        }
    }

    public void checkIsTitleAlreadyExistsByExceptId(UUID id, String title) throws EntityAlreadyExistsException {
        if(articleRepository.checkIsTitleAlreadyExistsByExceptId(id, title)) {
            throw new EntityAlreadyExistsException();
        }
    }
}

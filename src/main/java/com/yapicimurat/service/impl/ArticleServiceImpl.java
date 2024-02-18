package com.yapicimurat.service.impl;

import com.yapicimurat.controller.request.ArticleCreateRequest;
import com.yapicimurat.controller.request.ArticleUpdateRequest;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Article;
import com.yapicimurat.model.Category;
import com.yapicimurat.repository.ArticleRepository;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.service.CategoryService;
import com.yapicimurat.util.ConverterUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final CategoryService categoryService;

    private final ArticleRepository articleRepository;

    private final int TOTAL_PER_PAGE = 5;

    public ArticleServiceImpl(CategoryService categoryService,
                              ArticleRepository articleRepository) {
        this.categoryService = categoryService;
        this.articleRepository = articleRepository;
    }

    @Override
    public com.yapicimurat.dto.Pageable getAll(Integer currentPage) {
        currentPage = currentPage != null ? currentPage : 0;
        
        Pageable x = PageRequest.of(currentPage, TOTAL_PER_PAGE);
        Page<Article> allArticlesPage = articleRepository.findAll(x);

        List<Article> articles = allArticlesPage.get().collect(Collectors.toList());

        return new com.yapicimurat.dto.Pageable(
                articles,
                allArticlesPage.getTotalPages(),
                TOTAL_PER_PAGE,
                currentPage,
                allArticlesPage.hasNext(),
                allArticlesPage.hasPrevious()
        );
    }

    @Override
    public Boolean isTitleUsedByAnotherArticle(String title) {
        return articleRepository.isTitleUsedByAnother(title);
    }

    @Override
    public Boolean isTitleUsedByAnotherArticleWithId(UUID id, String title) throws EntityAlreadyExistsException {
        return articleRepository.isTitleUsedByAnotherWithId(id, title);
    }

    @Override
    public Optional<Article> getByIdOptional(UUID id) {
        return articleRepository.findById(id);
    }

    @Override
    public Article getById(UUID id) {
        return articleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public String create(final ArticleCreateRequest requestBody) {
        final boolean isTitleUsedByAnotherArticle = isTitleUsedByAnotherArticle(requestBody.getTitle());
        if(isTitleUsedByAnotherArticle){
            throw new EntityAlreadyExistsException();
        }

        final Article article = new Article();
        final Set<Category> categoryList = categoryService.getAllByIdIn(ConverterUtil.stringSetToUUIDSet(requestBody.getCategoryIdList()));

        article.setTitle(requestBody.getTitle());
        article.setDescription(requestBody.getDescription());
        article.setHtmlContent(requestBody.getHtmlContent());
        article.setReadTimeInMinute(requestBody.getReadTimeInMinute());
        article.setCategories(categoryList);

        return articleRepository.save(article).id.toString();
    }

    @Override
    public Article update(String id, ArticleUpdateRequest requestBody) {
        Article article = getById(UUID.fromString(id));
        boolean isTitleUsedByAnotherArticle = isTitleUsedByAnotherArticleWithId(article.getId(), requestBody.getTitle());

        if(isTitleUsedByAnotherArticle){
            throw new EntityAlreadyExistsException();
        }

        Set<Category> categories = categoryService.getAllByIdIn(ConverterUtil.stringSetToUUIDSet(requestBody.getCategories()));

        article.setTitle(requestBody.getTitle());
        article.setDescription(requestBody.getDescription());
        article.setHtmlContent(requestBody.getHtmlContent());
        article.setReadTimeInMinute(requestBody.getReadTimeInMinute());
        article.setCategories(categories);

        return articleRepository.save(article);
    }
}

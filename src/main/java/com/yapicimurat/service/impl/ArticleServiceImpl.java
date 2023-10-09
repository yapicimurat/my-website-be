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
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final CategoryService categoryService;

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(CategoryService categoryService,
                              ArticleRepository articleRepository) {
        this.categoryService = categoryService;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAll() {

        return articleRepository.findAll();
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
        final List<Category> categoryList = categoryService
                .getByIdList(ConverterUtil.stringListToUUIDList(requestBody.getCategoryIdList()));

        article.setTitle(requestBody.getTitle());
        article.setDescription(requestBody.getDescription());
        article.setHtmlContent(requestBody.getHtmlContent());
        article.setReadTimeInMinute(requestBody.getReadTimeInMinute());
        article.setCategories(categoryList);

        return articleRepository.save(article).id.toString();
    }

    @Override
    public Article update(String id, ArticleUpdateRequest requestBody) {
        final Article article = getById(UUID.fromString(id));
        final boolean isTitleUsedByAnotherArticle = isTitleUsedByAnotherArticleWithId(article.getId(), requestBody.getTitle());

        if(isTitleUsedByAnotherArticle){
            throw new EntityAlreadyExistsException();
        }

        final List<Category> categories = categoryService.getByIdList(ConverterUtil.stringListToUUIDList(requestBody.getCategories()));

        article.setTitle(requestBody.getTitle());
        article.setDescription(requestBody.getDescription());
        article.setHtmlContent(requestBody.getHtmlContent());
        article.setReadTimeInMinute(requestBody.getReadTimeInMinute());
        article.setCategories(categories);

        return articleRepository.save(article);
    }
}

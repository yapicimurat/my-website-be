package com.yapicimurat.service.impl;

import com.yapicimurat.common.mapper.ArticleMapper;
import com.yapicimurat.common.mapper.CategoryMapper;
import com.yapicimurat.dto.article.ArticleDTO;
import com.yapicimurat.dto.article.ArticleInputDTO;
import com.yapicimurat.dto.pageable.PageableDTO;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Article;
import com.yapicimurat.model.Category;
import com.yapicimurat.model.projection.ArticleCommentCountDTO;
import com.yapicimurat.model.projection.ArticleDetailDTO;
import com.yapicimurat.model.projection.ArticleSummaryDTO;
import com.yapicimurat.model.projection.CommentSummaryDTO;
import com.yapicimurat.repository.ArticleRepository;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.service.CategoryService;
import com.yapicimurat.service.CommentService;
import com.yapicimurat.util.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {
    private static final int TOTAL_ARTICLES_IN_PAGE = 4;
    private final ArticleRepository articleRepository;
    private final CategoryService categoryService;
    private final CommentService commentService;

    public ArticleServiceImpl(CategoryService categoryService,
                              ArticleRepository articleRepository,
                              CommentService commentService) {
        this.categoryService = categoryService;
        this.articleRepository = articleRepository;
        this.commentService = commentService;
    }

    @Override
    @Transactional(readOnly = true)
    public PageableDTO<ArticleSummaryDTO> getAll(Integer currentPage) {
        currentPage = CommonUtil.clampDataPageNumber(currentPage - 1);
        Pageable page = PageRequest.of(currentPage, TOTAL_ARTICLES_IN_PAGE);
        Page<Article> allArticlesPage = articleRepository.getAllArticles(page);
        List<ArticleSummaryDTO> articleSummaryDTOList = createArticleSummaryDTOListByArticleList(allArticlesPage.getContent());
        return new PageableDTO<>(
                articleSummaryDTOList,
                allArticlesPage.getTotalPages(),
                TOTAL_ARTICLES_IN_PAGE,
                currentPage,
                allArticlesPage.hasNext(),
                allArticlesPage.hasPrevious()
        );
    }

    private List<ArticleSummaryDTO> createArticleSummaryDTOListByArticleList(List<Article> articleList) {
        if(Objects.nonNull(articleList)) {
            Set<UUID> articleIdSet = articleList.stream().map(Article::getId).collect(Collectors.toSet());
            List<ArticleCommentCountDTO> results = articleRepository.findCommentCountsByArticleIds(articleIdSet);
            articleRepository.loadArticleCategories(articleIdSet);
            Map<UUID, Integer> commentsCountMappedByArticleId = results.stream()
                    .collect(Collectors.toMap(
                            ArticleCommentCountDTO::articleId,
                            dto -> dto.count().intValue()
                    ));
            return articleList.stream().map(article -> {
                Integer amountOfComments = commentsCountMappedByArticleId.get(article.getId());
               return new ArticleSummaryDTO(
                       article.getId().toString(),
                       article.getTitle(),
                       article.getDescription(),
                       article.getReadTimeInMinute(),
                       article.getCategories().stream().map(Category::getName).collect(Collectors.toSet()),
                       Objects.nonNull(amountOfComments) ? amountOfComments : 0,
                       article.getCreatedAt()
               );
               }).toList();
        }
        return null;
    }

    @Override
    public Optional<ArticleDTO> getOptionalById(UUID id) {
        return articleRepository.findById(id).map(ArticleMapper.INSTANCE::toArticleDTO);
    }

    @Override
    public ArticleDTO getById(UUID id) {
        return articleRepository.findById(id).map(ArticleMapper.INSTANCE::toArticleDTO)
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

    @Override
    public ArticleDetailDTO getArticleDetailById(UUID id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if(articleOptional.isPresent()) {
            Article article = articleOptional.get();
            PageableDTO<CommentSummaryDTO> commentDTOPageableDTO = commentService.getAllByArticle(id, 0);
            int articleCommentCount = getArticleCommentCount(article.getId());
            return ArticleMapper.INSTANCE
                    .toArticleDetailDTO(
                            article,
                            commentDTOPageableDTO,
                            articleCommentCount
                    );
        }
        return null;
    }

    private int getArticleCommentCount(UUID id) {
        List<ArticleCommentCountDTO> articleCommentCountDTOList = articleRepository.findCommentCountsByArticleIds(Set.of(id));
        if(Objects.nonNull(articleCommentCountDTOList) && !articleCommentCountDTOList.isEmpty()) {
            return articleCommentCountDTOList.get(0).count().intValue();
        }
        return 0;
    }

    private ArticleDTO saveCategory(@Nullable final UUID id, ArticleInputDTO articleInputDTO) {
        performPreChecksToSaveArticle(id, articleInputDTO);
        Set<Category> categoryEntitySet = new HashSet<>(CategoryMapper.INSTANCE
                .toCategoryList(categoryService.getAllByIdIn(articleInputDTO.categoryIds())));
        Article articleToSave = ArticleMapper.INSTANCE
                .convertArticleInputDTOToArticleEntity(articleInputDTO, categoryEntitySet);
        articleToSave.setId(id);
        return ArticleMapper.INSTANCE.toArticleDTO(articleRepository.save(articleToSave));
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

    public void checkIsTitleAlreadyExistsByExceptId(UUID id, String title) {
        if(articleRepository.checkIsTitleAlreadyExistsByExceptId(id, title)) {
            throw new EntityAlreadyExistsException();
        }
    }
}

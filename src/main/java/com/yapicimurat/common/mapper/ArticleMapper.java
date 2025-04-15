package com.yapicimurat.common.mapper;

import com.yapicimurat.dto.article.ArticleDTO;
import com.yapicimurat.dto.article.ArticleInputDTO;
import com.yapicimurat.dto.pageable.PageableDTO;
import com.yapicimurat.model.Article;
import com.yapicimurat.model.Category;
import com.yapicimurat.model.projection.ArticleDetailDTO;
import com.yapicimurat.model.projection.ArticleSummaryDTO;
import com.yapicimurat.model.projection.CommentSummaryDTO;
import com.yapicimurat.web.input.article.ArticleInput;
import com.yapicimurat.web.output.article.ArticleDetailOutput;
import com.yapicimurat.web.output.article.ArticleOutput;
import com.yapicimurat.web.output.article.ArticleSummaryOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    List<ArticleDTO> toArticleDTOList(List<Article> source);
    List<ArticleOutput> convertArticleDTOListToArticleOutputList(List<ArticleDTO> source);

    @Mapping(target = "amountOfAnswers", expression = "java(getAmountOfComments(source))")
    ArticleDTO toArticleDTO(Article source);

    ArticleOutput toArticleOutput(ArticleDTO source);
    ArticleSummaryOutput toArticleSummaryOutput(ArticleSummaryDTO source);
    List<ArticleSummaryOutput> toArticleSummaryOutputList(List<ArticleSummaryDTO> source);
    ArticleInputDTO toArticleInputDTO(ArticleInput source);
    Article toArticleEntity(ArticleDTO source);
    Article toArticle(ArticleInputDTO source, @MappingTarget Article article);

    @Mapping(target = "categories", expression = "java(getCategoryNames(article))")
    @Mapping(target = "totalComments", expression = "java(getAmountOfComments(article))")
    ArticleSummaryDTO toArticleSummaryDTO(Article article);
    List<ArticleSummaryDTO> toArticleSummaryDTOList(List<Article> source);

    ArticleDetailOutput toArticleDetailOutput(ArticleDetailDTO source);

    @Mapping(target = "categories", expression = "java(CategoryMapper.INSTANCE.toCategoryDTOSet(article.getCategories()))")
    @Mapping(target = "comments", source = "commentSummaryDTOPageableDTO")
    @Mapping(target = "totalCommentCount", source = "articleCommentCount")
    ArticleDetailDTO toArticleDetailDTO(Article article, PageableDTO<CommentSummaryDTO> commentSummaryDTOPageableDTO, int articleCommentCount);

    default Set<String> getCategoryNames(Article article) {
        if(Objects.nonNull(article.getCategories())) {
            return article.getCategories().stream().map(Category::getName).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }

    default Article convertArticleInputDTOToArticleEntity(ArticleInputDTO source, Set<Category> categoryEntitySet) {
        Article article = toArticle(source, new Article());
        article.setCategories(categoryEntitySet);
        return article;
    }
    default int getAmountOfComments(Article article) {
        return Objects.isNull(article.getComments()) ? 0 : article.getComments().size();
    }
}

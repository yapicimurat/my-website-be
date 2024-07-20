package com.yapicimurat.common.mapper;

import com.yapicimurat.dto.article.ArticleDTO;
import com.yapicimurat.dto.article.ArticleInputDTO;
import com.yapicimurat.model.Article;
import com.yapicimurat.model.Category;
import com.yapicimurat.web.input.article.ArticleInput;
import com.yapicimurat.web.output.article.ArticleOutput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
    List<ArticleDTO> convertArticleEntityListToArticleDTOList(List<Article> source);
    List<ArticleOutput> convertArticleDTOListToArticleOutputList(List<ArticleDTO> source);
    ArticleDTO convertArticleEntityToArticleDTO(Article source);
    ArticleOutput convertArticleDTOToArticleOutput(ArticleDTO source);
    ArticleInputDTO convertArticleInputToArticleInputDTO(ArticleInput source);

    Article convertArticleInputDTOToArticleEntity(ArticleInputDTO source, @MappingTarget Article article);

    default Article convertArticleInputDTOToArticleEntity(ArticleInputDTO source, Set<Category> categoryList) {
        Article article = convertArticleInputDTOToArticleEntity(source, new Article());
        article.setCategories(categoryList);
        return article;
    }
}

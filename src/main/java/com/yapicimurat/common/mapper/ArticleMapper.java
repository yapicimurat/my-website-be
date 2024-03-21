package com.yapicimurat.common.mapper;

import com.yapicimurat.dto.ArticleDTO;
import com.yapicimurat.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    List<ArticleDTO> convertArticleEntityListToArticleDTOList(List<Article> source);
    ArticleDTO convertArticleEntityToArticleDTO(Article source);

}

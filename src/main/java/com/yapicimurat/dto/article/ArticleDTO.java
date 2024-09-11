package com.yapicimurat.dto.article;

import com.yapicimurat.dto.category.CategoryDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleDTO(
        String id,
        String title,
        String description,
        String htmlContent,
        String coverImageURL,
        Byte readTimeInMinute,
        Set<CategoryDTO> categories,
        int amountOfAnswers,
        LocalDateTime createdAt
) {

}

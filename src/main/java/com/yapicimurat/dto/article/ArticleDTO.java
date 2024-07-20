package com.yapicimurat.dto.article;

import com.yapicimurat.dto.category.CategoryDTO;
import com.yapicimurat.dto.CommentDTO;

import java.util.List;
import java.util.Set;

public record ArticleDTO(
        String id,
        String title,
        String description,
        String htmlContent,
        String coverImageURL,
        Byte readTimeInMinute,
        Set<CategoryDTO> categories,
        List<CommentDTO> comments
) {

}

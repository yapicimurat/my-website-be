package com.yapicimurat.web.output.article;

import com.yapicimurat.dto.CommentDTO;
import com.yapicimurat.dto.category.CategoryDTO;

import java.util.List;
import java.util.Set;

public record ArticleOutput(
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

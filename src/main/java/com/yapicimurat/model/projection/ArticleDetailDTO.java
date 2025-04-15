package com.yapicimurat.model.projection;

import com.yapicimurat.dto.category.CategoryDTO;
import com.yapicimurat.dto.pageable.PageableDTO;
import java.time.LocalDateTime;
import java.util.Set;

public record ArticleDetailDTO(
        String id,
        String title,
        String description,
        int readTimeInMinute,
        String coverImageURL,
        String htmlContent,
        Set<CategoryDTO> categories,
        PageableDTO<CommentSummaryDTO> comments,
        int totalCommentCount,
        LocalDateTime createdAt
) {
}

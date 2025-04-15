package com.yapicimurat.web.output.article;

import com.yapicimurat.dto.category.CategoryDTO;
import com.yapicimurat.web.output.comment.CommentSummaryOutput;
import com.yapicimurat.web.output.pageable.PageableOutput;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleDetailOutput(
        String id,
        String title,
        String description,
        int readTimeInMinute,
        String coverImageURL,
        String htmlContent,
        Set<CategoryDTO> categories,
        PageableOutput<CommentSummaryOutput> comments,
        int totalCommentCount,
        LocalDateTime createdAt
) {}

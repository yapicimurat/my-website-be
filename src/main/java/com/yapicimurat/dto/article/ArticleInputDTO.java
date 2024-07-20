package com.yapicimurat.dto.article;

import com.yapicimurat.dto.CommentDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record ArticleInputDTO (
        String title,
        String description,
        String htmlContent,
        String coverImageURL,
        Byte readTimeInMinute,
        Set<UUID> categoryIdSet,
        List<CommentDTO> comments
) {
}

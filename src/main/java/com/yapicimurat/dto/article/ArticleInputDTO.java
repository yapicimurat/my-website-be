package com.yapicimurat.dto.article;

import java.util.Set;
import java.util.UUID;

public record ArticleInputDTO (
        String title,
        String description,
        String htmlContent,
        String coverImageURL,
        Byte readTimeInMinute,
        Set<UUID> categoryIds
) {
}

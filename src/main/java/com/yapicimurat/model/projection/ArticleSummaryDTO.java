package com.yapicimurat.model.projection;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleSummaryDTO(
        String id,
        String title,
        String description,
        int readTimeInMinute,
        Set<String> categories,
        int totalComments,
        LocalDateTime createdAt
) {
}

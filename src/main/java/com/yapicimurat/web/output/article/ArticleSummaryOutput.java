package com.yapicimurat.web.output.article;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleSummaryOutput(
        String id,
        String title,
        String description,
        Byte readTimeInMinute,
        Set<String> categories,
        int totalComments,
        LocalDateTime createdAt
) {
}

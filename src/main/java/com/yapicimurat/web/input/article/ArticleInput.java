package com.yapicimurat.web.input.article;


import java.util.Set;

public record ArticleInput(
        String title,
        String description,
        String htmlContent,
        Byte readTimeInMinute,
        Set<String> categoryIds
) {
}

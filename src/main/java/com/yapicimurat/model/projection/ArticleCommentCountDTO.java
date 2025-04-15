package com.yapicimurat.model.projection;

import java.util.UUID;

public record ArticleCommentCountDTO(
        UUID articleId,
        Long count
) {
}

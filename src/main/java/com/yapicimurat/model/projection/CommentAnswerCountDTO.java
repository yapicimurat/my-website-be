package com.yapicimurat.model.projection;

import java.util.UUID;

public record CommentAnswerCountDTO(
        UUID id,
        UUID parentCommentId,
        UUID rootCommentId,
        int count
) {
}

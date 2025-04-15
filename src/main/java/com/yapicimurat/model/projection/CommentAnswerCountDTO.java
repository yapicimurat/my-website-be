package com.yapicimurat.model.projection;

import java.util.UUID;

public record CommentAnswerCountDTO(
        UUID id,
        int count
) {
}

package com.yapicimurat.model.projection;

import java.time.LocalDateTime;

public record CommentSummaryDTO(
        String id,
        String name,
        String lastName,
        String email,
        String text,
        Boolean isAnswer,
        int amountOfAnswers,
        LocalDateTime createdAt,
        CommentSummaryDTO parentComment,
        CommentSummaryDTO rootComment
) {
}

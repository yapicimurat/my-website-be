package com.yapicimurat.web.output.comment;

import java.time.LocalDateTime;

public record CommentSummaryOutput(
        String id,
        String name,
        String lastName,
        String email,
        String text,
        Boolean isAnswer,
        int amountOfAnswers,
        CommentSummaryOutput parentComment,
        CommentSummaryOutput rootComment,
        LocalDateTime createdAt
) {
}

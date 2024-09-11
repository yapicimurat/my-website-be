package com.yapicimurat.web.output.comment;

import java.time.LocalDateTime;

public record ParentCommentOutput(
        String id,
        String name,
        String lastName,
        String email,
        String text,
        Boolean isAnswer,
        Integer amountOfAnswers,
        LocalDateTime createdAt
) implements CommentBaseOutput {
}

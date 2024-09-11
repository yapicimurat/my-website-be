package com.yapicimurat.dto.comment;

import java.time.LocalDateTime;

public record ParentCommentDTO(
        String id,
        String name,
        String lastName,
        String email,
        String text,
        Boolean isAnswer,
        Integer amountOfAnswers,
        LocalDateTime createdAt
) {
}

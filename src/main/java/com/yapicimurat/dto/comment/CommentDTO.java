package com.yapicimurat.dto.comment;

import java.time.LocalDateTime;

public record CommentDTO(
        String id,
        String name,
        String lastName,
        String email,
        String text,
        Boolean isAnswer,
        ParentCommentDTO parentComment,
        int amountOfAnswers,
        LocalDateTime createdAt
){
}

package com.yapicimurat.web.output.comment;

import com.yapicimurat.dto.comment.CommentDTO;

import java.time.LocalDateTime;
import java.util.List;

public record CommentOutput(
        String id,
        String name,
        String lastName,
        String email,
        String text,
        Boolean isAnswer,
        ParentCommentOutput parentComment,
        int amountOfAnswers,
        LocalDateTime createdAt
) implements CommentBaseOutput {
}

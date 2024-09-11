package com.yapicimurat.dto.comment;

public record CommentInputDTO(
        String name,
        String lastName,
        String email,
        String text,
        Boolean isAnswer,
        String parentCommentId
) {
}

package com.yapicimurat.web.input.comment;

public record CommentInput(
        String name,
        String lastName,
        String email,
        String text,
        Boolean isAnswer,
        String parentCommentId
) {
}

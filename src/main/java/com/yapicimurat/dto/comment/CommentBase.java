package com.yapicimurat.dto.comment;

import java.time.LocalDateTime;

public interface CommentBase {
    String id();
    String name();
    String lastName();
    String email();
    String text();
    Boolean isAnswer();
    LocalDateTime createdAt();
}

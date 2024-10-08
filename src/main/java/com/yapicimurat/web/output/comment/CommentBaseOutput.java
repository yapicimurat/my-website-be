package com.yapicimurat.web.output.comment;

import java.time.LocalDateTime;

public interface CommentBaseOutput {
    String id();
    String name();
    String lastName();
    String email();
    String text();
    Boolean isAnswer();
    LocalDateTime createdAt();
}

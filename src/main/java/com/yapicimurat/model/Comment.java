package com.yapicimurat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "COMMENT")
public class Comment extends BaseModel {
    @NotBlank
    @Size(max = 30)
    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

    @NotBlank
    @Size(max = 30)
    @Column(name = "LAST_NAME", length = 30, nullable = false)
    private String lastName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @NotBlank
    @Size(max = 3000)
    @Column(name = "TEXT", length = 3000, nullable = false)
    private String text;

    @Column(name = "IS_ANSWER")
    private boolean isAnswer = false;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Article article;

    @JsonBackReference
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentComment")
    private List<Comment> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parentComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "root_comment_id")
    private Comment rootComment;

    public List<Comment> getNoProxyAnswers() {
        return answers != null ? this.answers : Collections.emptyList();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Comment> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Comment> answers) {
        this.answers = answers;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Comment getRootComment() {
        return rootComment;
    }

    public void setRootComment(Comment rootComment) {
        this.rootComment = rootComment;
    }
}

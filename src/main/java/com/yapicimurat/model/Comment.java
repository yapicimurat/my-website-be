package com.yapicimurat.model;

import com.yapicimurat.model.abs.BaseModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COMMENT")
public class Comment extends BaseModel {
    @Column(name = "NAME", length = 30)
    private String name;
    @Column(name = "LAST_NAME", length = 30)
    private String lastName;
    @Column(name = "EMAIL", length = 50)
    private String email;
    @Column(name = "COMMENT", length = 3000)
    private String text;
    @Column(name = "IS_ANSWER")
    private Boolean isAnswer = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ARTICLE_COMMENT",
            joinColumns = @JoinColumn(name = "ARTICLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "COMMENT_ID")

    )
    private Article article;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentComment")
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "COMMENT_ANSWER",
            joinColumns = @JoinColumn(name = "PARENT_COMMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "ANSWER_COMMENT_ID")
    )
    private Comment parentComment;

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

    public Boolean getAnswer() {
        return isAnswer;
    }

    public void setAnswer(Boolean answer) {
        isAnswer = answer;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Comment getParentComment() {
        return parentComment;
    }
    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }
}

package com.yapicimurat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yapicimurat.model.abs.BaseModel;
import com.yapicimurat.util.GeneralUtil;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "COMMENT")
public class Comment extends BaseModel {
    @Column(name = "NAME", length = 30)
    private String name;
    @Column(name = "LAST_NAME", length = 30)
    private String lastName;
    @Column(name = "EMAIL", length = 50)
    private String email;
    @Column(name = "TEXT", length = 3000)
    private String text;
    @Column(name = "IS_ANSWER")
    private Boolean isAnswer = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Article article;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentComment")
    @JsonBackReference
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
        if(GeneralUtil.isNullOrEmpty(name)) return;
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(GeneralUtil.isNullOrEmpty(lastName)) return;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(GeneralUtil.isNullOrEmpty(email)) return;
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(GeneralUtil.isNullOrEmpty(text)) return;
        this.text = text;
    }

    public Boolean getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Boolean answer) {
        if(Objects.isNull(answer)) return;
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

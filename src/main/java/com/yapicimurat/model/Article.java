package com.yapicimurat.model;

import com.yapicimurat.model.abs.BaseModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ARTICLE")
public class Article extends BaseModel {
    @Column(name = "TITLE", length = 300)
    private String title;
    @Column(name = "DESCRIPTION", length = 500)
    private String description;
    @Column(name = "HTML_CONTENT", length = 5000)
    private String htmlContent;
    @Column(name = "READ_TIME_IN_MINUTES")
    private Byte readTimeInMinute;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ARTICLE_CATEGORY",
            joinColumns = @JoinColumn(name = "ARTICLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ARTICLE_CATEGORY_ID")
    )
    private List<Category> articleCategories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<Comment> comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public Byte getReadTimeInMinute() {
        return readTimeInMinute;
    }

    public void setReadTimeInMinute(Byte readTimeInMinute) {
        this.readTimeInMinute = readTimeInMinute;
    }

    public List<Category> getArticleCategories() {
        return articleCategories;
    }

    public void setArticleCategories(List<Category> articleCategories) {
        this.articleCategories = articleCategories;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
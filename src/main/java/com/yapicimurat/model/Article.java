package com.yapicimurat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yapicimurat.model.abs.BaseModel;
import com.yapicimurat.util.GeneralUtil;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ARTICLE")
public class Article extends BaseModel {
    @Column(name = "TITLE", length = 300)
    private String title;
    @Column(name = "DESCRIPTION", length = 500)
    private String description;
    @Column(name = "HTML_CONTENT", length = 5000)
    private String htmlContent;

    @Column(name = "COVER_IMAGE_URL", length = 1000)
    private String coverImageURL;

    @Column(name = "READ_TIME_IN_MINUTES")
    private Byte readTimeInMinute;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ARTICLE_CATEGORY",
            joinColumns = @JoinColumn(name = "ARTICLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "ARTICLE_CATEGORY_ID")
    )
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    @JsonManagedReference
    private List<Comment> comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(GeneralUtil.isNullOrEmpty(title)) return;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(GeneralUtil.isNullOrEmpty(description)) return;
        this.description = description;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        if(GeneralUtil.isNullOrEmpty(htmlContent)) return;
        this.htmlContent = htmlContent;
    }

    public Byte getReadTimeInMinute() {
        return readTimeInMinute;
    }

    public void setReadTimeInMinute(Byte readTimeInMinute) {
        if(Objects.isNull(readTimeInMinute)) return;
        this.readTimeInMinute = readTimeInMinute;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        if(Objects.isNull(categories)) return;
        this.categories = categories;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        if(Objects.isNull(comments)) return;
        this.comments = comments;
    }

    public String getCoverImageURL() {
        return coverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }
}
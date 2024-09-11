package com.yapicimurat.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ARTICLE")
public class Article extends BaseModel {
    @NotBlank
    @Size(max = 300)
    @Column(name = "TITLE", length = 300, nullable = false)
    private String title;

    @NotBlank
    @Size(max = 500)
    @Column(name = "DESCRIPTION", length = 500, nullable = false)
    private String description;

    @NotBlank
    @Size(max = 5000)
    @Column(name = "HTML_CONTENT", length = 5000, nullable = false)
    private String htmlContent;

    @Size(max = 1000)
    @Column(name = "COVER_IMAGE_URL", length = 1000)
    private String coverImageURL;

    @NotNull
    @Column(name = "READ_TIME_IN_MINUTES")
    private int readTimeInMinute;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ARTICLE_CATEGORY",
    joinColumns = @JoinColumn(name = "ARTICLE_ID"),
    inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
    private Set<Category> categories = new HashSet<>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<Comment> comments;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(Objects.isNull(title)) return;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(Objects.isNull(title)) return;
        this.description = description;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        if(Objects.isNull(title)) return;
        this.htmlContent = htmlContent;
    }

    public int getReadTimeInMinute() {
        return readTimeInMinute;
    }

    public void setReadTimeInMinute(Byte readTimeInMinute) {
        if(Objects.isNull(readTimeInMinute)) return;
        this.readTimeInMinute = readTimeInMinute;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        if(Objects.isNull(categories)) return;
        this.categories = categories;
    }
    public List<Comment> getComments() {
        return comments;
    }

    public int getAmountOfAnswers() {
        if(Objects.isNull(comments)) return 0;
        return comments.size();
    }

    public void setComments(List<Comment> comments) {
        if(Objects.isNull(comments)) return;
        this.comments = comments;
    }

    public String getCoverImageURL() {
        return coverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        if(Objects.isNull(title)) return;
        this.coverImageURL = coverImageURL;
    }
}
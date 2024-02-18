package com.yapicimurat.controller.request;

import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;

public class ArticleUpdateRequest {
    @Length(min = 10, max = 300)
    private String title;
    @Length(min = 10, max = 500)
    private String description;
    @Length(min = 10, max = 5000)
    private String htmlContent;
    private Byte readTimeInMinute;

    private Set<String> categories;

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

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
}

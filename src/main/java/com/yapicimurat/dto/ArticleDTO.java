package com.yapicimurat.dto;

import java.util.List;

public class ArticleDTO extends BaseDTO {
    private String title;
    private String description;
    private String htmlContent;
    private Byte readTimeInMinute;
    private List<CategoryDTO> categories;
    private List<CommentDTO> comments;

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

    public List<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}

package com.yapicimurat.controller.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ArticleCreateRequest {
    @NotNull
    @NotBlank
    @Length(min = 10, max = 300)
    private String title;
    @NotNull
    @NotBlank
    @Length(min = 10, max = 500)
    private String description;
    @NotNull
    @NotBlank
    @Length(min = 10, max = 5000)
    private String htmlContent;
    @NotNull
    private Byte readTimeInMinute;
    @NotNull
    private List<String> categoryIdList;

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

    public List<String> getCategoryIdList() {
        return categoryIdList;
    }

    public void setCategoryIdList(List<String> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }
}

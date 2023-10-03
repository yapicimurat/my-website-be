package com.yapicimurat.controller.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoryCreateRequest {
    @NotNull
    @NotBlank
    @Min(value = 3)
    private String name;
    @NotNull
    @NotBlank
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

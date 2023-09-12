package com.yapicimurat.model;

import com.yapicimurat.model.abs.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseModel {
    @Column(name = "NAME", length = 100)
    private String name;
    @Column(name = "COLOR", length = 50)
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

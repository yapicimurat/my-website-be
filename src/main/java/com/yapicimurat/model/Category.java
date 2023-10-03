package com.yapicimurat.model;

import com.yapicimurat.model.abs.BaseModel;
import com.yapicimurat.util.GeneralUtil;

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
        if(GeneralUtil.isNullOrEmpty(name)) return;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if(GeneralUtil.isNullOrEmpty(color)) return;
        this.color = color;
    }
}

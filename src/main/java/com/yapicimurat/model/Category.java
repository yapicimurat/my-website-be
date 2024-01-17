package com.yapicimurat.model;

import com.yapicimurat.model.abs.BaseModel;
import com.yapicimurat.util.GeneralUtil;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseModel {
    @Column(name = "NAME", length = 100)
    @Size(max = 100)
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

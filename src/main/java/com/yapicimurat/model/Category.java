package com.yapicimurat.model;

import com.yapicimurat.model.abs.BaseModel;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseModel {
    @NotBlank
    @Size(max = 100)
    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @NotBlank
    @Size(max = 9)
    @Column(name = "COLOR", length = 9, nullable = false)
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(Objects.isNull(name)) return;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if(Objects.isNull(color)) return;
        this.color = color;
    }
}

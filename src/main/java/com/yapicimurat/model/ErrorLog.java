package com.yapicimurat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ERROR_LOG")
public class ErrorLog extends BaseModel {
    @Column(name = "REQUESTED_PATH")
    private String requestedPath;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    public String getRequestedPath() {
        return requestedPath;
    }

    public void setRequestedPath(String requestedPath) {
        this.requestedPath = requestedPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

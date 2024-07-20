package com.yapicimurat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class Client extends BaseModel {
    @Column(name = "USER_AGENT")
    private String userAgent;

    @Column(name = "HTTP_METHOD")
    private String httpMethod;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
}

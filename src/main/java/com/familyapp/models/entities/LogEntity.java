package com.familyapp.models.entities;

public class LogEntity extends BaseEntity {

    private String requestUrl;
    private String user;
    private Long lastAccessed;
    private Long timeTaken;

    public LogEntity() {
    }


    public String getRequestUrl() {
        return requestUrl;
    }

    public String getUser() {
        return user;
    }

    public Long getLastAccessed() {
        return lastAccessed;
    }

    public Long getTimeTaken() {
        return timeTaken;
    }


    public LogEntity setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
        return this;
    }

    public LogEntity setUser(String user) {
        this.user = user;
        return this;
    }

    public LogEntity setLastAccessed(Long lastAccessed) {
        this.lastAccessed = lastAccessed;
        return this;
    }

    public LogEntity setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
        return this;
    }
}

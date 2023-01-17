package com.accenture.codingtest.springbootcodingtest.model;



public class TaskDto {


    private String id;


    private String title;


    private String description;


    private String status;


    private String projectId;


    private String userId;
    private String projectName;
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String project_id) {
        this.projectId = project_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
    }

    public void setProjectName(String name) {
        this.projectName=name;
    }

    public void setUsername(String username) {
        this.username=username;
    }
}

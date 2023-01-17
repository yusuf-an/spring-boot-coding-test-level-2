package com.accenture.codingtest.springbootcodingtest.entity;

import com.accenture.codingtest.springbootcodingtest.enums.STATUS_ENUMS;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import static com.accenture.codingtest.springbootcodingtest.enums.STATUS_ENUMS.NOT_STARTED;

@Entity
public class Task {

    @Id
    @Column(name = "id", length = 40)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private STATUS_ENUMS status=NOT_STARTED;


    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="project_id", nullable=false)
    private Project project;

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

    public STATUS_ENUMS getStatus() {
        return status;
    }

    public void setStatus(STATUS_ENUMS status) {
        this.status = status;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", user=" + user +
                ", project=" + project +
                '}';
    }
}

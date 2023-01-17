package com.accenture.codingtest.springbootcodingtest.mappers;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.model.ProjectDto;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectDto convertProjectEntityToProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        return projectDto;
    }

    public Project convertProjectDtoToProjectEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setId(projectDto.getId());
        project.setName(projectDto.getName());
        return project;
    }
}

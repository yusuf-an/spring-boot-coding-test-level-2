package com.accenture.codingtest.springbootcodingtest.controller;


import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.mappers.ProjectMapper;
import com.accenture.codingtest.springbootcodingtest.model.ProjectDto;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/project")
public class ProjectController {

    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    ProjectService projectService;

    @PostMapping
    public ProjectDto create(@RequestBody ProjectDto projectDto) {
        Project projectEntity = projectMapper.convertProjectDtoToProjectEntity(projectDto);
        try {
            return projectMapper.convertProjectEntityToProjectDto(
                    projectService.save(projectEntity));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping
    public List<ProjectDto> findAll() {
        return projectService.findAll().stream().map(m -> projectMapper.convertProjectEntityToProjectDto(m))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProjectDto findById(@PathVariable String id) {
        return projectService.findById(id).map(m -> projectMapper.convertProjectEntityToProjectDto(m))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "project not found for id: "+id));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        try {
             projectService.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        return id;
    }

    @PutMapping("/{id}")
    public ProjectDto renameProject(@PathVariable String id, @RequestBody ProjectDto projectDto) {
        try {
            Project project=projectService.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "project not found for id: "+id));
            project.setName(projectDto.getName());
            return projectMapper.convertProjectEntityToProjectDto(projectService.save(project));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }
}

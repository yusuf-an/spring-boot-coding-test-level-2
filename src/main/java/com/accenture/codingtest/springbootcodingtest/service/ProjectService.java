package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;


    public Project save(Project project) {
        return projectRepository.save(project);
    }


    public List<Project> findAll() {
        return projectRepository.findAll();
    }


    public Optional<Project> findById(String id) {
        return projectRepository.findById(id);
    }

    public void deleteById(String id) {
        projectRepository.deleteById(id);
    }
}

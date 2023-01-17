package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.accenture.codingtest.springbootcodingtest.enums.STATUS_ENUMS;
import com.accenture.codingtest.springbootcodingtest.mappers.TaskMapper;
import com.accenture.codingtest.springbootcodingtest.model.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    TaskMapper taskMapper;

    @PostMapping
    public TaskDto create(@RequestBody TaskDto taskDto) {
        try {
            Task task = taskService.save(taskMapper.convertTaskDtoToTaskEntity(taskDto));
            return taskMapper.convertTaskEntityToTaskDto(task);
        }catch (Exception e){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(),e);
        }
    }

    @PatchMapping("{taskId}/status/")
    public TaskDto updateStatus(@PathVariable String taskId, @RequestBody TaskDto taskDto) {
        Task task = taskService.updateStatus(taskId,STATUS_ENUMS.valueOf(taskDto.getStatus()));
        return taskMapper.convertTaskEntityToTaskDto(task);
    }

    @GetMapping
    public List<TaskDto> findAll() {
        return taskService.findAll().stream().map(m->taskMapper.convertTaskEntityToTaskDto(m)).collect(Collectors.toList());
    }

    @GetMapping("/project/{projectId}")
    public List<TaskDto> findByProjectId(@PathVariable String projectId) {
        return taskService.findByProjectId(projectId).stream().map(m->taskMapper.convertTaskEntityToTaskDto(m))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<TaskDto> findByUserId(@PathVariable String userId) {
        return taskService.findByUserId(userId).stream().map(m->taskMapper.convertTaskEntityToTaskDto(m))
                .collect(Collectors.toList());
    }
}
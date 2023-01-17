package com.accenture.codingtest.springbootcodingtest.mappers;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.enums.STATUS_ENUMS;
import com.accenture.codingtest.springbootcodingtest.model.TaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDto convertTaskEntityToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus().toString());
        taskDto.setProjectId(task.getProject().getId());
        taskDto.setProjectName(task.getProject().getName());
        taskDto.setUserId(task.getUser().getId());
        taskDto.setUsername(task.getUser().getUsername());

        return taskDto;
    }

    public Task convertTaskDtoToTaskEntity(TaskDto taskDto) {
        Task task = new Task();
        User user=new User();
        user.setId(taskDto.getUserId());
        Project project=new Project();
        project.setId(taskDto.getProjectId());
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(STATUS_ENUMS.valueOf(taskDto.getStatus()));
        task.setProject(project);
        task.setUser(user);
        return task;
    }
}

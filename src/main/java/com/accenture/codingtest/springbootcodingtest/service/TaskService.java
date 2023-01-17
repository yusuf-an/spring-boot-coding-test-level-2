package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.enums.STATUS_ENUMS;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService{

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProjectRepository projectRepository;
	public Task save(Task task) {
		if(!userRepository.existsById(task.getUser().getId())){
			throw new RuntimeException("user does not exist for task-id:"+task.getUser().getId());
		}
		if(!projectRepository.existsById(task.getProject().getId())){
			throw new RuntimeException("project does not exist for project-id:"+task.getProject().getId());
		}
		return taskRepository.save(task);
	}


	public Optional<Task> findById(String id) {
		return taskRepository.findById(id);
	}


	public List<Task> findAll() {
		return taskRepository.findAll();
	}


	public List<Task> findByProjectId(String projectId) {
		return taskRepository.findByProjectId(projectId);
	}


	public List<Task> findByUserId(String userId) {
		return taskRepository.findByUserId(userId);
	}


	public Task update(Task task, String id) {
		Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("task not found for id: "+id));
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setStatus(task.getStatus());
		return taskRepository.save(existingTask);
	}

	public Task updateStatus(String taskId, STATUS_ENUMS toStatus) {
		Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("task not found for id: "+taskId));
		existingTask.setStatus(toStatus);
		return taskRepository.save(existingTask);
	}
}
package com.crm.service;

import com.crm.dto.TaskRequest;
import com.crm.dto.TaskUpdateRequest;
import com.crm.entity.Task;
import com.crm.entity.TaskStatus;
import com.crm.exception.TaskNotFoundException;
import com.crm.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(TaskRequest request) {

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())

                // Default PENDING only if status is not sent
                .status(
                        request.getStatus() != null
                                ? request.getStatus()
                                : TaskStatus.PENDING
                )

                .dueDate(request.getDueDate())
                .createdAt(LocalDateTime.now())
                .build();

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {

        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id : " + id));
    }

    public Task updateTask(
            Long id,
            TaskUpdateRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id : " + id));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id : " + id));

        taskRepository.delete(task);
    }
}
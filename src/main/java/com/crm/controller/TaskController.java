package com.crm.controller;

import com.crm.dto.ApiResponse;
import com.crm.dto.TaskRequest;
import com.crm.dto.TaskUpdateRequest;
import com.crm.entity.Task;
import com.crm.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ApiResponse<Task> createTask(
            @Valid @RequestBody TaskRequest request) {

        return new ApiResponse<>(
                true,
                "Task Created Successfully",
                taskService.createTask(request)
        );
    }

    @GetMapping
    public ApiResponse<List<Task>> getAllTasks() {

        return new ApiResponse<>(
                true,
                "Tasks Fetched Successfully",
                taskService.getAllTasks()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<Task> getTaskById(
            @PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Task Found",
                taskService.getTaskById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Task> updateTask(
            @PathVariable Long id,
            @RequestBody TaskUpdateRequest request) {

        return new ApiResponse<>(
                true,
                "Task Updated Successfully",
                taskService.updateTask(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteTask(
            @PathVariable Long id) {

        taskService.deleteTask(id);

        return new ApiResponse<>(
                true,
                "Task Deleted Successfully",
                null
        );
    }
}
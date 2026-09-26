package com.crm.dto;

import com.crm.entity.TaskPriority;
import com.crm.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private TaskPriority priority;

    private TaskStatus status;

    private LocalDateTime dueDate;
}
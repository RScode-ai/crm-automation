package com.crm.dto;

import com.crm.entity.TaskPriority;
import com.crm.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskUpdateRequest {

    private String title;
    private String description;
    private TaskPriority priority;
    private TaskStatus status;
    private LocalDateTime dueDate;
}
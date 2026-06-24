package com.crm.repository;

import com.crm.entity.Task;
import com.crm.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    long countByStatus(TaskStatus status);
}
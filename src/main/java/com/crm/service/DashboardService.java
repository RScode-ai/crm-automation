package com.crm.service;

import com.crm.dto.DashboardStats;
import com.crm.entity.TaskStatus;
import com.crm.repository.ContactRepository;
import com.crm.repository.LeadRepository;
import com.crm.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final LeadRepository leadRepository;
    private final ContactRepository contactRepository;
    private final TaskRepository taskRepository;

    public DashboardService(
            LeadRepository leadRepository,
            ContactRepository contactRepository,
            TaskRepository taskRepository) {

        this.leadRepository = leadRepository;
        this.contactRepository = contactRepository;
        this.taskRepository = taskRepository;
    }

    public DashboardStats getStats() {

        return DashboardStats.builder()
                .totalLeads(leadRepository.count())
                .totalContacts(contactRepository.count())
                .totalTasks(taskRepository.count())
                .pendingTasks(
                        taskRepository.countByStatus(
                                TaskStatus.PENDING))
                .completedTasks(
                        taskRepository.countByStatus(
                                TaskStatus.COMPLETED))
                .build();
    }
}
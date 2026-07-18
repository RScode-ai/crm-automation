package com.crm.service;

import com.crm.dto.DashboardStats;
import com.crm.dto.RecentLeadResponse;
import com.crm.entity.TaskStatus;
import com.crm.repository.ContactRepository;
import com.crm.repository.LeadRepository;
import com.crm.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

        List<RecentLeadResponse> recentLeads =
                leadRepository.findTop5ByOrderByCreatedAtDesc()
                        .stream()
                        .map(lead -> RecentLeadResponse.builder()
                                .id(lead.getId())
                                .name(lead.getName())
                                .company(lead.getCompany())
                                .status(lead.getStatus().name())
                                .createdAt(lead.getCreatedAt())
                                .build())
                        .toList();

        return DashboardStats.builder()
                .totalLeads(
                        leadRepository.count()
                )
                .totalContacts(
                        contactRepository.count()
                )
                .totalTasks(
                        taskRepository.count()
                )
                .pendingTasks(
                        taskRepository.countByStatus(
                                TaskStatus.PENDING
                        )
                )
                .completedTasks(
                        taskRepository.countByStatus(
                                TaskStatus.COMPLETED
                        )
                )
                .recentLeads(
                        recentLeads
                )
                .build();
    }

}
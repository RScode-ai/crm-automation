package com.crm.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStats {

    private long totalLeads;

    private long totalContacts;

    private long totalTasks;

    private long pendingTasks;

    private long completedTasks;

    private List<RecentLeadResponse> recentLeads;

}
package com.crm.controller;

import com.crm.dto.ApiResponse;
import com.crm.dto.DashboardStats;
import com.crm.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService) {

        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public ApiResponse<DashboardStats> getStats() {

        return new ApiResponse<>(
                true,
                "Dashboard Stats Fetched Successfully",
                dashboardService.getStats()
        );
    }
}
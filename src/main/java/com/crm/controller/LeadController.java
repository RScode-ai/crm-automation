package com.crm.controller;

import com.crm.dto.*;
import java.util.List;
import jakarta.validation.Valid;
import com.crm.entity.Lead;
import com.crm.service.LeadService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leads")

public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    @PostMapping
    public ApiResponse<Lead> createLead(
            @Valid @RequestBody LeadRequest request) {

        Lead lead = leadService.createLead(request);

        return new ApiResponse<>(
                true,
                "Lead Created Successfully",
                lead
        );
    }


    @GetMapping
    public ApiResponse<List<Lead>> getAllLeads() {

        return new ApiResponse<>(
                true,
                "Leads Fetched Successfully",
                leadService.getAllLeads()
        );
    }


    @GetMapping("/{id}")
    public ApiResponse<Lead> getLeadById(
            @PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Lead Found",
                leadService.getLeadById(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Lead> updateLead(
            @PathVariable Long id,
            @RequestBody LeadUpdateRequest request) {

        return new ApiResponse<>(
                true,
                "Lead Updated Successfully",
                leadService.updateLead(id, request)
        );
    }



    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteLead(
            @PathVariable Long id) {

        leadService.deleteLead(id);

        return new ApiResponse<>(
                true,
                "Lead Deleted Successfully",
                null
        );
    }

    @PutMapping("/assign")
    public ApiResponse<Lead> assignLead(
            @RequestBody
            LeadAssignmentRequest request){

        return new ApiResponse<>(
                true,
                "Lead Assigned Successfully",
                leadService.assignLead(
                        request.getLeadId(),
                        request.getSalesUserId()
                )
        );
    }


    @PutMapping("/status")
    public ApiResponse<Lead> updateStatus(
            @RequestBody
            LeadStatusUpdateRequest request){

        return new ApiResponse<>(
                true,
                "Lead Status Updated",
                leadService.updateStatus(
                        request.getLeadId(),
                        request.getStatus()
                )
        );
    }


}
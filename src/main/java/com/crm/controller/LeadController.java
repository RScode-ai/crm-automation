package com.crm.controller;

import com.crm.dto.LeadRequest;
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
    public Lead createLead(@RequestBody LeadRequest request) {
        return leadService.createLead(request);
    }
}
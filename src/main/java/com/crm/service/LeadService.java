package com.crm.service;

import com.crm.dto.LeadRequest;
import com.crm.entity.Lead;
import com.crm.entity.LeadStatus;
import com.crm.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public Lead createLead(LeadRequest request) {

        Lead lead = Lead.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .company(request.getCompany())
                .source(request.getSource())
                .status(LeadStatus.NEW)
                .createdAt(LocalDateTime.now())
                .build();

        return leadRepository.save(lead);
    }
}
package com.crm.service;

import com.crm.dto.*;
import java.util.List;
import com.crm.entity.Lead;
import com.crm.exception.*;
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
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }
    public Lead getLeadById(Long id) {

        return leadRepository.findById(id)
                .orElseThrow(() ->
                        new LeadNotFoundException("Lead not found with id : " + id));
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

    public Lead updateLead(Long id, LeadUpdateRequest request) {

        Lead lead = leadRepository.findById(id)
                .orElseThrow(() ->
                        new LeadNotFoundException("Lead not found with id : " + id));

        lead.setName(request.getName());
        lead.setEmail(request.getEmail());
        lead.setPhone(request.getPhone());
        lead.setCompany(request.getCompany());
        lead.setSource(request.getSource());
        lead.setStatus(request.getStatus());

        return leadRepository.save(lead);
    }
    public void deleteLead(Long id) {

        Lead lead = leadRepository.findById(id)
                .orElseThrow(() ->
                        new LeadNotFoundException("Lead not found with id : " + id));

        leadRepository.delete(lead);
    }


}
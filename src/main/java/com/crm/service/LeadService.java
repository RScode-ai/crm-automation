package com.crm.service;

import com.crm.dto.*;
import java.util.List;
import com.crm.entity.Lead;
import com.crm.entity.User;
import com.crm.exception.*;
import com.crm.entity.LeadStatus;
import com.crm.repository.LeadRepository;
import com.crm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LeadService {

    private final LeadRepository leadRepository;

    private final UserRepository userRepository;

    private final LeadActivityService leadActivityService;

    public LeadService(LeadRepository leadRepository, UserRepository userRepository , LeadActivityService leadActivityService) {
        this.leadRepository = leadRepository;
        this.userRepository = userRepository;
        this.leadActivityService = leadActivityService;
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

        Lead savedLead =
                leadRepository.save(lead);

        leadActivityService.logActivity(
                savedLead,
                "Lead Created"
        );

        return savedLead;


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


    public Lead assignLead(
            Long leadId,
            Long salesUserId) {

        Lead lead = leadRepository
                .findById(leadId)
                .orElseThrow();

        User user = userRepository
                .findById(salesUserId)
                .orElseThrow();

        lead.setAssignedTo(user);

        Lead updatedLead = leadRepository.save(lead);

        leadActivityService.logActivity(
                updatedLead,
                "Assigned To : " + user.getName()
        );

        return updatedLead;
    }

    public Lead updateStatus(
            Long leadId,
            LeadStatus status) {

        Lead lead = leadRepository
                .findById(leadId)
                .orElseThrow();

        LeadStatus oldStatus = lead.getStatus();

        lead.setStatus(status);

        Lead updatedLead = leadRepository.save(lead);

        leadActivityService.logActivity(
                updatedLead,
                "Status Changed : "
                        + oldStatus
                        + " -> "
                        + status
        );

        return updatedLead;
    }




}
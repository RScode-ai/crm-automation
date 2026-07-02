package com.crm.service;

import com.crm.entity.Lead;
import com.crm.entity.LeadActivity;
import com.crm.repository.LeadActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.crm.entity.LeadActivity.*;

@Service
public class LeadActivityService {

    private final LeadActivityRepository repository;

    public LeadActivityService(
            LeadActivityRepository repository) {

        this.repository = repository;
    }

    public void logActivity(
            Lead lead,
            String action) {

        LeadActivity activity =
                LeadActivity.builder()
                        .lead(lead)
                        .activity(action)
                        .createdAt(LocalDateTime.now())
                        .build();

        repository.save(activity);
    }

    public List<LeadActivity> getActivities(
            Long leadId) {

        return repository.findByLeadId(leadId);
    }
}
package com.crm.repository;

import com.crm.entity.LeadActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadActivityRepository
        extends JpaRepository<LeadActivity, Long> {

    List<LeadActivity> findByLeadId(Long leadId);
}
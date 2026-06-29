package com.crm.dto;

import com.crm.entity.LeadStatus;
import lombok.Data;

@Data
public class LeadStatusUpdateRequest {

    private Long leadId;

    private LeadStatus status;
}
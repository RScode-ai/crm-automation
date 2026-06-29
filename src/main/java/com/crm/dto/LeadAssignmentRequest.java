package com.crm.dto;

import lombok.Data;

@Data
public class LeadAssignmentRequest {

    private Long leadId;

    private Long salesUserId;
}
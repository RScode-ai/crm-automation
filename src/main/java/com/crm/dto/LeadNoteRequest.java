package com.crm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeadNoteRequest {

    private Long leadId;

    private String note;
}
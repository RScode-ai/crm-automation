package com.crm.dto;

import com.crm.entity.LeadStatus;
import lombok.Data;

@Data
public class LeadUpdateRequest {

    private String name;
    private String email;
    private String phone;
    private String company;
    private String source;
    private LeadStatus status;
}
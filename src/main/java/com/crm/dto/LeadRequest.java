package com.crm.dto;

import lombok.Data;

@Data
public class LeadRequest {

    private String name;
    private String email;
    private String phone;
    private String company;
    private String source;

}
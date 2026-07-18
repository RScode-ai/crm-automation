package com.crm.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentLeadResponse {

    private Long id;

    private String name;

    private String company;

    private String status;

    private LocalDateTime createdAt;

}
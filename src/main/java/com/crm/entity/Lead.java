package com.crm.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String company;

    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private User assignedTo;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    private String source;

    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "lead",
            cascade = CascadeType.ALL
    )
    private List<LeadActivity> activities;

    @OneToMany(
            mappedBy = "lead",
            cascade = CascadeType.ALL
    )
    private java.util.List<LeadNote> notes;
}
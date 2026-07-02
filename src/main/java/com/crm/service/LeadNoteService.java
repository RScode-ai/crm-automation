package com.crm.service;

import com.crm.dto.LeadNoteRequest;
import com.crm.entity.Lead;
import com.crm.entity.LeadNote;
import com.crm.repository.LeadNoteRepository;
import com.crm.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeadNoteService {

    private final LeadRepository leadRepository;
    private final LeadNoteRepository leadNoteRepository;

    public LeadNoteService(
            LeadRepository leadRepository,
            LeadNoteRepository leadNoteRepository) {

        this.leadRepository = leadRepository;
        this.leadNoteRepository = leadNoteRepository;
    }

    public LeadNote addNote(
            LeadNoteRequest request) {

        Lead lead = leadRepository
                .findById(request.getLeadId())
                .orElseThrow();

        LeadNote note = LeadNote.builder()
                .note(request.getNote())
                .createdAt(LocalDateTime.now())
                .lead(lead)
                .build();

        return leadNoteRepository.save(note);
    }

    public List<LeadNote> getLeadNotes(
            Long leadId) {

        return leadNoteRepository
                .findByLeadId(leadId);
    }
}
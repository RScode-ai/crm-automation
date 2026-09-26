package com.crm.controller;

import com.crm.dto.ApiResponse;
import com.crm.dto.LeadNoteRequest;
import com.crm.entity.LeadNote;
import com.crm.service.LeadNoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lead-notes")
public class LeadNoteController {

    private final LeadNoteService leadNoteService;

    public LeadNoteController(
            LeadNoteService leadNoteService) {

        this.leadNoteService = leadNoteService;
    }

    @PostMapping
    public ApiResponse<LeadNote> addNote(
            @RequestBody LeadNoteRequest request) {

        return new ApiResponse<>(
                true,
                "Note Added Successfully",
                leadNoteService.addNote(request)
        );
    }

    @GetMapping("/{leadId}")
    public ApiResponse<List<LeadNote>> getNotes(
            @PathVariable Long leadId) {

        return new ApiResponse<>(
                true,
                "Notes Fetched Successfully",
                leadNoteService.getLeadNotes(leadId)
        );
    }
}
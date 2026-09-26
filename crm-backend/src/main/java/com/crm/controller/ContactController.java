package com.crm.controller;

import com.crm.dto.ApiResponse;
import com.crm.dto.ContactRequest;
import com.crm.dto.ContactUpdateRequest;
import com.crm.entity.Contact;
import com.crm.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Create Contact
    @PostMapping
    public ApiResponse<Contact> createContact(
            @Valid @RequestBody ContactRequest request) {

        Contact contact = contactService.createContact(request);

        return new ApiResponse<>(
                true,
                "Contact Created Successfully",
                contact
        );
    }

    // Get All Contacts
    @GetMapping
    public ApiResponse<List<Contact>> getAllContacts() {

        return new ApiResponse<>(
                true,
                "Contacts Fetched Successfully",
                contactService.getAllContacts()
        );
    }

    // Get Contact By Id
    @GetMapping("/{id}")
    public ApiResponse<Contact> getContactById(
            @PathVariable Long id) {

        return new ApiResponse<>(
                true,
                "Contact Found",
                contactService.getContactById(id)
        );
    }

    // Update Contact
    @PutMapping("/{id}")
    public ApiResponse<Contact> updateContact(
            @PathVariable Long id,
            @Valid @RequestBody ContactUpdateRequest request) {

        return new ApiResponse<>(
                true,
                "Contact Updated Successfully",
                contactService.updateContact(id, request)
        );
    }

    // Delete Contact
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteContact(
            @PathVariable Long id) {

        contactService.deleteContact(id);

        return new ApiResponse<>(
                true,
                "Contact Deleted Successfully",
                null
        );
    }
}
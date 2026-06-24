package com.crm.service;

import com.crm.dto.ContactRequest;
import com.crm.entity.Contact;
import com.crm.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(ContactRequest request) {

        Contact contact = Contact.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .company(request.getCompany())
                .designation(request.getDesignation())
                .address(request.getAddress())
                .createdAt(LocalDateTime.now())
                .build();

        return contactRepository.save(contact);
    }
}
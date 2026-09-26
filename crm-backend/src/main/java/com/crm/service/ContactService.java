package com.crm.service;

import com.crm.dto.ContactRequest;
import com.crm.dto.ContactUpdateRequest;
import com.crm.entity.Contact;
import java.util.List;
import com.crm.exception.ContactNotFoundException;
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
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {

        return contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundException(
                                "Contact not found with id : " + id));
    }

    public Contact updateContact(
            Long id,
            ContactUpdateRequest request) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundException(
                                "Contact not found with id : " + id));

        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setCompany(request.getCompany());
        contact.setDesignation(request.getDesignation());
        contact.setAddress(request.getAddress());

        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundException(
                                "Contact not found with id : " + id));

        contactRepository.delete(contact);
    }
}
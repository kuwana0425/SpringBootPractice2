package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.repository.ContactRepository;

@Controller
@RequestMapping("/admin/contacts")
public class ContactlistController {

    @Autowired
    private ContactRepository contactRepository;
	private Contact contactForm;

    @GetMapping
    public String listContacts(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "contact_list";
    }

    @GetMapping("/{id}")
    public String contactDetails(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        model.addAttribute("contact", contact);
        return "contact_details";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        model.addAttribute("contact", contact);
        return "contact_edit";
    }

    @PostMapping("/{id}/edit")
    public String editContact(@PathVariable Long id, @ModelAttribute ContactForm form, RedirectAttributes redirectAttributes) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        contact.setLastName(form.getLastName());
        contact.setFirstName(form.getFirstName());
        contact.setEmail(form.getEmail());
        contact.setPhone(form.getPhone());
        contact.setZipCode(form.getZipCode());
        contact.setAddress(form.getAddress());
        contact.setBuildingName(form.getBuildingName());
        contact.setContactType(form.getContactType());
        contact.setBody(form.getBody());

        contactRepository.save(contact);
        redirectAttributes.addFlashAttribute("message", "Contact updated successfully");

        return "redirect:/admin/contacts";
    }

    @PostMapping("/{id}/delete")
    public String deleteContact(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid contact Id:" + id));
        contactRepository.delete(contact);
        redirectAttributes.addFlashAttribute("message", "Contact deleted successfully");
        
        return "redirect:/admin/contacts";
    }
}
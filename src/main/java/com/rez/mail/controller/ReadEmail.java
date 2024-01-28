package com.rez.mail.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.rez.mail.data.Mail;
import com.rez.mail.data.MailFolder;
import com.rez.mail.read.ReadEmailService;

import jakarta.mail.MessagingException;


@Controller
public class ReadEmail {

    private ReadEmailService emailService;

    public ReadEmail(ReadEmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/folders")
    public ResponseEntity<Object> read() throws MessagingException {
        List<MailFolder> emails = emailService.readInboundEmails();
        return new ResponseEntity<Object>(emails, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getInbox() throws MessagingException {
        List<Mail> inbox = emailService.getInboxEmails();
        return new ResponseEntity<Object>(inbox, HttpStatus.OK);
    }
}

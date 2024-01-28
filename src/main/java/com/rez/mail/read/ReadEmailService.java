package com.rez.mail.read;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rez.mail.data.Mail;
import com.rez.mail.data.MailFolder;
import com.rez.mail.data.MessageToMail;
import com.rez.mail.store.UserStore;
import com.sun.mail.imap.IMAPFolder;

import jakarta.mail.AuthenticationFailedException;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeMultipart;

@Service
public class ReadEmailService {

    private static final Logger logger = LoggerFactory.getLogger(ReadEmailService.class);

    public List<MailFolder> readInboundEmails() {
        List<MailFolder> folders = new ArrayList<>();
        try {
            // connect to message store
            Store store = UserStore.getInstance();
            Arrays.stream(store.getDefaultFolder().list("*"))
                    .forEach(folder -> {
                        try {
                            folder.open(Folder.READ_ONLY);
                            folders.add(new MailFolder(folder));
                            folder.close();
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    });

        } catch (AuthenticationFailedException e) {
            logger.error("Exception in reading EMails : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folders;
    }

    public Message[] readEmails() {
        Message[] messages = null;
        try {
            // connect to message store
            Store store = UserStore.getInstance();
            IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            // fetch messages
            messages = inbox.getMessages(1, 25);
            logger.info("Total Mails : " + messages.length);
            ((MimeMultipart) messages[0].getContent()).getBodyPart(0);
            inbox.close();
        } catch (AuthenticationFailedException e) {
            logger.error("Exception in reading EMails : " + e.getMessage());
        } catch (MessagingException e) {
            logger.error("Exception in reading EMails : " + e.getMessage());
        } catch (Exception e) {
            logger.error("Exception in reading EMails : " + e.getMessage());
        }
        return messages;
    }

    public List<Mail> getInboxEmails() throws MessagingException {
        Store store = UserStore.getInstance();
        IMAPFolder inbox = (IMAPFolder) store.getFolder("INBOX");
        if(!inbox.isOpen())
            inbox.open(Folder.READ_ONLY);

        List<Mail> mails = Arrays.asList(inbox.getMessages(10, 25)).stream().map(msg -> {
            try {
                return MessageToMail.convertMessageToMail(msg);
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        if(inbox.isOpen())
            inbox.close();

        return mails;
    }

}
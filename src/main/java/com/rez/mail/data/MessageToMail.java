package com.rez.mail.data;

import java.io.IOException;

import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMultipart;

public class MessageToMail {

    public static Mail convertMessageToMail(Message message) throws MessagingException, IOException {
        return new Mail(
                message.getFrom()[0].toString(),
                message.getSubject(),
                message.getRecipients(RecipientType.TO),
                message.getRecipients(RecipientType.CC),
                message.getContent().getClass().equals(String.class) ? null : (MimeMultipart) message.getContent(),
                message.getContentType());
    }

}

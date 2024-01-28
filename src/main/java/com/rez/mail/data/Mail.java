package com.rez.mail.data;

import java.io.IOException;
import java.util.Arrays;

import com.rez.mail.util.ReadMailFormat;

import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMultipart;

public final class Mail {

    private final String from;
    private final String subject;
    private final String contentType;
    private final MimeMultipart body;
    private final Address[] toAddress;
    private final Address[] ccAddress;

    public Mail(String from, String subject, Address[] toAddress, Address[] ccAddress, MimeMultipart body, String contentType) {
        this.from = from;
        this.subject = subject;
        this.toAddress = toAddress;
        this.ccAddress = ccAddress;
        this.body = body;
        this.contentType = contentType;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getContentType() {
        return contentType;
    }

    public Address[] getToAddress() {
        return toAddress;
    }

    public Address[] getCcAddress() {
        return ccAddress;
    }

    public String fetchTextMessage() {
        return getMessageForType(ReadMailFormat.PLAIN_TEXT);
    }

    public String fetchRichMessage() {
        return getMessageForType(ReadMailFormat.RICH_TEXT_WITH_IMAGE);
    }

    public String convertAddressesToString(Address[] address) {
        return String.join(", ", Arrays.stream(address).map(Address::toString).toList());
    }

    private String getMessageForType(ReadMailFormat format) {
        try {
            return this.body.getBodyPart(format.index).getContent().toString();
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }
        return "";
    }
}

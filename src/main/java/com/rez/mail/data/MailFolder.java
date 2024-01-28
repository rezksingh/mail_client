package com.rez.mail.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.mail.Folder;
import jakarta.mail.MessagingException;

public class MailFolder {

    private String name;
    private int totalSize;
    private int unreadMessageSize;
    private int newMessageSize;
    private LocalDateTime lastRead;
    private List<String> messages = new ArrayList<>();

    public MailFolder(Folder folder) throws MessagingException {
        this.name = folder.getName();
        this.totalSize = folder.getMessageCount();
        this.unreadMessageSize = folder.getUnreadMessageCount();
        this.newMessageSize = folder.getNewMessageCount();
        this.lastRead = LocalDateTime.now();
        // messages = Stream.of(folder.getMessages()).map(msg -> {
        //     try {
        //         if(msg.getContentType().startsWith("TEXT")) {
        //             return msg.getContent().toString();
        //         }
        //         return ((MimeMultipart) msg.getContent()).getBodyPart(ReadMailFormat.PLAIN_TEXT.index).toString();
        //     } catch (MessagingException | IOException e) {
        //         e.printStackTrace();
        //     }
        //     return null;
        // }).collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public int getUnreadMessageSize() {
        return unreadMessageSize;
    }

    public int getNewMessageSize() {
        return newMessageSize;
    }

    public LocalDateTime getLastRead() {
        return lastRead;
    }

    public List<String> getMessages() {
        return messages;
    }

}

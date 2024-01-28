package com.rez.mail.store;

import jakarta.mail.MessagingException;
import jakarta.mail.Store;

public class UserStore {

    private UserStore() {}

    public static Store getInstance() {
        try {
            Store store = IMAPSession.getInstance().getIMAPSessionStore();
            if(!store.isConnected())
                store.connect("imap.mail.com", 993, "EMAIL_ID", "PASSWORD");
            return store;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

}

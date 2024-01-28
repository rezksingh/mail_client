package com.rez.mail.store;

import java.util.Properties;

import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Store;

public class IMAPSession {

    private static IMAPSession IMAPSession = null;

    private Properties props;
    private Session session;

    private final String PROTOCOL = "imap";
    private final String IMAP_HOST = "imap.mail.com";
    private final String IMAP_PORT = "993";

    private IMAPSession() {
        configureProps();
        this.session = Session.getDefaultInstance(props, null);
    }

    public static synchronized IMAPSession getInstance() {
        if (IMAPSession == null)
            IMAPSession = new IMAPSession();

        return IMAPSession;
    }

    public Store getIMAPSessionStore() throws NoSuchProviderException {
        return session.getStore(PROTOCOL);
    }

    private void configureProps() {
        props = new Properties();
        props.setProperty("mail.imap.ssl.enable", "true");
        props.setProperty("mail.store.protocol", PROTOCOL);
        props.setProperty("mail.imap.host", IMAP_HOST);
        props.setProperty("mail.imap.port", IMAP_PORT);
    }
}

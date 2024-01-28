package com.rez.mail.util;

public enum ReadMailFormat {
    PLAIN_TEXT(0, "Plain text message extracted from MimeMultiPart's BodyPart's `0` Index Content"),
    RICH_TEXT_WITH_IMAGE(1, "HTML message extracted from MimeMultiPart's BodyPart's `1` Index Content");

    public final int index;
    public final String label;

    private ReadMailFormat(int part, String label) {
        this.index = part;
        this.label = label;
    }
}

package com.beard.util;

import org.apache.commons.codec.digest.DigestUtils;

public final class PasswordEncryption {


    private static final String SALT = "Beard@123456789#";

    private PasswordEncryption() {
    }

    public static String encryption(String unencryptedString) {
        return DigestUtils.md5Hex(unencryptedString + SALT);
    }
}

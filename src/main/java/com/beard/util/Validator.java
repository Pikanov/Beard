package com.beard.util;

public final class Validator {

    private static final String VALID_EMAIL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final String VALID_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String VALID_PHONE_NUMBER = "^[0-9]{10}$";

    private Validator() {
    }

    public static boolean isEmailValid(String email) {
        return email.matches(VALID_EMAIL);
    }

    public static boolean isPasswordValid(String password) {
        return password.matches(VALID_PASSWORD);
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches(VALID_PHONE_NUMBER);
    }
}

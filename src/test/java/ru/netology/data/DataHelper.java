package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String number;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("**** **** **** 0001"); // ПРАВИЛЬНЫЙ ФОРМАТ
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("**** **** **** 0002"); // ПРАВИЛЬНЫЙ ФОРМАТ
    }
}

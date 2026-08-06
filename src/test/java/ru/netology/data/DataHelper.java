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
        String maskedNumber;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("5559 0000 0000 0001", "**** **** **** 0001");
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("5559 0000 0000 0002", "**** **** **** 0002");
    }
}

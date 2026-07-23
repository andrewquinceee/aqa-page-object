package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginInput = $("[data-test-id='login'] input");
    private SelenideElement passwordInput = $("[data-test-id='password'] input");
    private SelenideElement actionButton = $(".button");

    public VerificationPage validLogin(String login, String password) {
        loginInput.setValue(login);
        passwordInput.setValue(password);
        actionButton.click();
        return new VerificationPage();
    }
}

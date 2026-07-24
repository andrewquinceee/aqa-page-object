package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginInput = $("[data-test-id='login'] input");
    private SelenideElement passwordInput = $("[data-test-id='password'] input");
    
    // ИСПРАВЛЕНО: ищем кнопку по тексту "Продолжить", так как data-test-id отсутствует
    private SelenideElement actionButton = $("button").shouldHave(text("Продолжить"));

    public VerificationPage validLogin(String login, String password) {
        loginInput.shouldBe(visible).setValue(login);
        passwordInput.shouldBe(visible).setValue(password);
        
        actionButton.shouldBe(visible).scrollTo().click();
        
        return new VerificationPage();
    }
}

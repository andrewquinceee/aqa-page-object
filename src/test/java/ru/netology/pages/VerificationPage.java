package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("button");

    public DashboardPage validVerify(String code) {
        codeInput.shouldBe(visible).setValue(code);
        verifyButton.shouldBe(visible).shouldHave(text("Продолжить")).scrollTo().click();
        
        // Возвращаем новую страницу. Её конструктор подождет загрузки.
        return new DashboardPage();
    }
}

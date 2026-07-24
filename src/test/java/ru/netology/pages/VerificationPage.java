package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public DashboardPage validVerify(String code) {
        codeInput.shouldBe(visible).setValue(code);
        verifyButton.shouldBe(visible).click();

        // !!! ГЛАВНОЕ ИСПРАВЛЕНИЕ !!!
        // Ждем появления элемента следующей страницы (кнопки "Пополнить"), 
        // чтобы убедиться, что редирект прошел и дашборд загрузился.
        $("[data-test-id='action-deposit']").shouldBe(visible);

        return new DashboardPage();
    }
}

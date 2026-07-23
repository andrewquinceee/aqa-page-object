package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    // Атрибут data-test-id находится на div, а input внутри него
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement actionButton = $(".button");
    private SelenideElement errorNotification = $(".notification");

    public DashboardPage transfer(String amount, String fromCard) {
        amountInput.setValue(amount);
        fromInput.setValue(fromCard);
        actionButton.click();
        return new DashboardPage();
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка"));
    }
}

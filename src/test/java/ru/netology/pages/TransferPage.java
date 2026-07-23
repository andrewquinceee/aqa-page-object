package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement actionButton = $("[type='submit']");
    private SelenideElement errorNotification = $(".notification");

    public DashboardPage transfer(String amount, String fromCard) {
        amountInput.setValue(amount);
        fromInput.setValue(fromCard);
        actionButton.click();
        return new DashboardPage();
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(com.codeborne.selenide.Condition.visible).shouldHave(text("Ошибка"));
    }
}

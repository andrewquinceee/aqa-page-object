package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id='amount'] input");
    private SelenideElement fromInput = $("[data-test-id='from'] input");
    private SelenideElement actionButton = $("[data-test-id='action-transfer']");

    public DashboardPage transferMoney(int amount, String fromCardNumber) {
        amountInput.shouldBe(visible).setValue(String.valueOf(amount));
        fromInput.shouldBe(visible).setValue(fromCardNumber);
        actionButton.shouldBe(visible).click();
        return new DashboardPage();
    }
}

package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");

    public TransferPage() {
        transferButton.shouldBe(visible);
    }

    public DashboardPage transferMoney(int amount, String fromCardNumber) {
        amountField.shouldBe(visible).setValue(String.valueOf(amount));
        fromField.shouldBe(visible).setValue(fromCardNumber);
        transferButton.shouldBe(visible).click();
        
        return new DashboardPage();
    }
}

package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput = $("[data-test-id='code'] input");
    private SelenideElement actionButton = $(".button");

    public DashboardPage validVerify(String code) {
        codeInput.setValue(code);
        actionButton.click();
        return new DashboardPage();
    }
}

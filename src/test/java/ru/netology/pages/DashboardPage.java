package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private ElementsCollection cards = $$("[data-test-id='card']");
    private ElementsCollection topUpButtons = $$("[data-test-id='action-deposit']");

    public DashboardPage() {
        cards.first().shouldBe(visible);
    }

    public int getCardBalance(int cardIndex) {
        SelenideElement card = cards.get(cardIndex);
        String cardText = card.getText();

        int startIndex = cardText.toLowerCase().indexOf("баланс:") + "баланс:".length();
        int endIndex = cardText.indexOf("р.", startIndex);

        if (startIndex == -1 || endIndex == -1) {
            throw new AssertionError("Не удалось найти баланс в тексте карты: " + cardText);
        }

        String balanceString = cardText.substring(startIndex, endIndex).trim().replace(" ", "");
        return Integer.parseInt(balanceString);
    }

    public TransferPage selectCardToTopUp(int cardIndex) {
        topUpButtons.get(cardIndex).shouldBe(visible).click();
        return new TransferPage();
    }
}

package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;

public class DashboardPage {

    // Все блоки карт
    private ElementsCollection cards = $$("[data-test-id=card]");
    // Все кнопки "Пополнить" у карт
    private ElementsCollection topUpButtons = $$("[data-test-id=action-deposit]");

    public DashboardPage() {
        // Убеждаемся, что хотя бы одна карта видна
        cards.first().shouldBe(visible);
        topUpButtons.first().shouldBe(visible);
    }

    // Получение баланса карты по индексу (0 - первая, 1 - вторая)
    public int getCardBalance(int cardIndex) {
        SelenideElement card = cards.get(cardIndex);
        String cardText = card.getText();

        String balanceStart = "Баланс: ";
        String balanceFinish = " р.";

        int start = cardText.indexOf(balanceStart);
        int finish = cardText.indexOf(balanceFinish);

        String balanceString = cardText.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(balanceString);
    }

    // Выбор карты для пополнения по индексу
    public TransferPage selectCardToTopUp(int cardIndex) {
        SelenideElement button = topUpButtons.get(cardIndex);
        button.click();
        return new TransferPage();
    }
}

package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");

    public TransferPage selectCardToTopUp(String cardNumber) {
        // 1. Ждем загрузки дашборда
        $("[data-test-id='dashboard']").shouldBe(visible);
        
        // 2. Если висит ошибка с предыдущих действий, закрываем её, чтобы она не перекрывала кнопку
        if ($("[data-test-id='error-notification']").is(visible)) {
            $("[data-test-id='error-notification'] .notification__closer").click();
        }
        
        // 3. Находим карточку по номеру и кликаем кнопку "Пополнить" внутри неё
        $x("//div[contains(., '" + cardNumber + "')]//button[contains(., 'Пополнить')]").shouldBe(visible).click();
        
        return new TransferPage();
    }

    public int getCardBalance(String cardNumber) {
        SelenideElement card = cards.filterBy(text(cardNumber)).first();
        String cardText = card.getText();
        String[] parts = cardText.split("баланс:");
        if (parts.length > 1) {
            return Integer.parseInt(parts[1].replaceAll("\\D+", ""));
        }
        throw new AssertionError("Не удалось найти слово 'баланс:' в тексте карточки: " + cardText);
    }
}

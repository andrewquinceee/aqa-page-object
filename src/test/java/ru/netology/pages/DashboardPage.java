package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");

    public DashboardPage() {
        try {
            // Ждем появления кнопки "Пополнить" — это самый надежный признак дашборда
            $("[data-test-id='action-deposit']").shouldBe(visible, Duration.ofSeconds(20));
        } catch (Exception e) {
            // ЕСЛИ НЕ УДАЛОСЬ: распечатываем текст страницы, чтобы увидеть ошибку приложения
            System.err.println("=== ОШИБКА: Дашборд не загрузился! Текст страницы: ===");
            System.err.println($("body").getText());
            System.err.println("=======================================================");
            throw e;
        }
    }

    public int getCardBalance(int cardIndex) {
        SelenideElement card = cards.get(cardIndex);
        String balanceText = card.find(".list__item__balance").getText();
        return Integer.parseInt(balanceText.replaceAll("\\D+", ""));
    }

    public TransferPage selectCardToTopUp(int cardIndex) {
        cards.get(cardIndex).find("[data-test-id='action-deposit']").shouldBe(visible).click();
        return new TransferPage();
    }
}

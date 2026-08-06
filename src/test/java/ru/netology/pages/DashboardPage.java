package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardPage {
    public DashboardPage() {
        // Увеличиваем таймаут ожидания карточек
        Configuration.timeout = 15000;
        $$(".list__item").first().shouldBe(visible);
    }

    public int getCardBalance(String cardNumber) {
        ElementsCollection cards = $$(".list__item");
        SelenideElement card = cards.findBy(text(cardNumber));
        String cardText = card.getText();
        
        Pattern pattern = Pattern.compile("баланс:\\s*(\\d+)\\s*р\\.");
        Matcher matcher = pattern.matcher(cardText);
        
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        
        throw new RuntimeException("Не удалось извлечь баланс из текста: " + cardText);
    }

    public TransferPage selectCardToTopUp(String cardNumber) {
        ElementsCollection cards = $$(".list__item");
        SelenideElement card = cards.findBy(text(cardNumber));
        card.find("[data-test-id='action-deposit']").shouldBe(visible).click();
        return new TransferPage();
    }
}

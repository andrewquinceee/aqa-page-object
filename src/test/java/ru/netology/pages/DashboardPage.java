package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private ElementsCollection cards = $$(".list__item");

    public DashboardPage() {
        // Теперь этот код сработает, так как VerificationPage уже подождал загрузки
        cards.first().shouldBe(visible);
    }

    public int getCardBalance(int cardIndex) {
        SelenideElement card = cards.get(cardIndex);
        String cardText = card.getText();

        // Надежная регулярка: ищет "баланс:" (в любом регистре), затем цифры и пробелы
        Pattern pattern = Pattern.compile("(?i)баланс:\\s*([\\d\\s]+)");
        Matcher matcher = pattern.matcher(cardText);

        if (matcher.find()) {
            // Удаляем пробелы (превращаем "10 000" в "10000") и парсим в int
            String balanceString = matcher.group(1).replace(" ", "").trim();
            return Integer.parseInt(balanceString);
        }

        throw new AssertionError("Не удалось найти баланс в тексте карты: " + cardText);
    }

    public TransferPage selectCardToTopUp(int cardIndex) {
        cards.get(cardIndex).find("[data-test-id='action-deposit']").shouldBe(visible).click();
        return new TransferPage();
    }
}

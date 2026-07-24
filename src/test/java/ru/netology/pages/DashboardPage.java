package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    // В приложении app-ibank блоки карт имеют класс list__item
    private ElementsCollection cards = $$(".list__item");

    public DashboardPage() {
        // Ждем появления хотя бы одной карты на странице
        cards.first().shouldBe(visible);
    }

    public int getCardBalance(int cardIndex) {
        SelenideElement card = cards.get(cardIndex);
        String cardText = card.getText();

        // Используем регулярное выражение для надежного поиска баланса
        // Ищет слово "баланс:" (в любом регистре), затем любые цифры и пробелы
        Pattern pattern = Pattern.compile("(?i)баланс:\\s*([\\d\\s]+)");
        Matcher matcher = pattern.matcher(cardText);

        if (matcher.find()) {
            // Удаляем все пробелы из найденной строки (превращаем "10 000" в "10000")
            String balanceString = matcher.group(1).replace(" ", "").trim();
            return Integer.parseInt(balanceString);
        }

        throw new AssertionError("Не удалось найти баланс в тексте карты: " + cardText);
    }

    public TransferPage selectCardToTopUp(int cardIndex) {
        // Находим кнопку "Пополнить" внутри конкретной карты и кликаем
        // В app-ibank у этой кнопки есть data-test-id="action-deposit"
        cards.get(cardIndex).find("[data-test-id='action-deposit']").shouldBe(visible).click();
        return new TransferPage();
    }
}

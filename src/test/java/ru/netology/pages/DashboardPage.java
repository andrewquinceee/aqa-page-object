package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");

    public DashboardPage() {
        cards.first().shouldBe(visible);
    }

    public int getCardBalance(int cardIndex) {
        SelenideElement card = cards.get(cardIndex);
        String balanceText = card.find(".list__item__balance").getText();
        
        Pattern pattern = Pattern.compile("(?i)баланс:\\s*([\\d\\s]+)");
        Matcher matcher = pattern.matcher(balanceText);
        
        if (matcher.find()) {
            String cleanBalance = matcher.group(1).replace(" ", "").trim();
            return Integer.parseInt(cleanBalance);
        }
        throw new AssertionError("Не удалось найти баланс в тексте: " + balanceText);
    }

    public TransferPage selectCardToTopUp(int cardIndex) {
        cards.get(cardIndex).find("[data-test-id='action-deposit']").shouldBe(visible).click();
        return new TransferPage();
    }
}

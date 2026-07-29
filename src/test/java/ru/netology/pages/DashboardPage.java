package ru.netology.pages;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;

public class DashboardPage {
    private ElementsCollection cards = $$(".list__item");

    public DashboardPage() {
        cards.first().shouldBe(visible);
    }

    public int getCardBalance(String cardNumber) {
        SelenideElement card = cards.findBy(text(cardNumber));
        String balanceText = card.find(".list__item__balance").getText();
        return Integer.parseInt(balanceText.replaceAll("\\D+", ""));
    }

    public TransferPage selectCardToTopUp(String cardNumber) {
        SelenideElement card = cards.findBy(text(cardNumber));
        card.find("[data-test-id='action-deposit']").shouldBe(visible).click();
        return new TransferPage();
    }
}

package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.VerificationPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "http://localhost:9999";
        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        
        open("/");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsSuccessfully() {
        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin("vasya", "qwerty123");
        DashboardPage dashboardPage = verificationPage.validVerify("12345");

        // Используем индексы: 0 - первая карта, 1 - вторая карта
        int cardToTopUpIndex = 1;
        int cardFromIndex = 0;
        String cardFromNumber = "5559 0000 0000 0001";
        int amount = 1000;

        int balanceBefore = dashboardPage.getCardBalance(cardToTopUpIndex);
        
        dashboardPage.selectCardToTopUp(cardToTopUpIndex)
                     .transferMoney(amount, cardFromNumber);

        int balanceAfter = dashboardPage.getCardBalance(cardToTopUpIndex);
        
        assertEquals(balanceBefore + amount, balanceAfter, 
            "Баланс карты получателя должен увеличиться на сумму перевода");
    }
}

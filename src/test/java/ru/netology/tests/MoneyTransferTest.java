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
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "http://localhost:9999";
        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        
        open("/");
        loginPage = new LoginPage();
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsSuccessfully() {
        VerificationPage verificationPage = loginPage.validLogin("vasya", "qwerty123");
        dashboardPage = verificationPage.validVerify("12345");

        String cardNumberFrom = "5559 0000 0000 0001";
        int amountToTransfer = 1000;

        int initialBalance = dashboardPage.getCardBalance(1);

        dashboardPage.selectCardToTopUp(1)
                .transferMoney(amountToTransfer, cardNumberFrom);

        int actualBalance = dashboardPage.getCardBalance(1);
        int expectedBalance = initialBalance + amountToTransfer;

        assertEquals(expectedBalance, actualBalance,
                "Баланс второй карты должен был увеличиться на " + amountToTransfer);
    }
}

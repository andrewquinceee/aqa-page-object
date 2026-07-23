package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        Configuration.baseUrl = "http://localhost:9999";
        // Убрали pageLoadStrategy = "eager", чтобы избежать гонок при переходе между страницами
        open("/");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsSuccessfully() {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = loginPage.validLogin("vasya", "qwerty123")
                                               .validVerify("12345");

        String cardToTopUp = "0001";
        String cardToTransferFrom = "0002";
        int transferAmount = 1000;

        int initialBalance = dashboardPage.getCardBalance(cardToTopUp);

        dashboardPage.selectCardToTopUp(cardToTopUp)
                     .transfer(String.valueOf(transferAmount), cardToTransferFrom);

        int expectedBalance = initialBalance + transferAmount;
        int actualBalance = dashboardPage.getCardBalance(cardToTopUp);
        
        assertEquals(expectedBalance, actualBalance);
    }
}

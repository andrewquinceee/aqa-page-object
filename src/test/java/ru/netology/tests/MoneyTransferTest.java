package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "http://localhost:9999";
        Configuration.browser = "firefox"; // <-- ЖЕСТКО ЗАДАЕМ FIREFOX
        open("/");
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsSuccessfully() {
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode();
        DataHelper.CardInfo firstCard = DataHelper.getFirstCard();
        DataHelper.CardInfo secondCard = DataHelper.getSecondCard();
        
        int amount = 1000;

        LoginPage loginPage = new LoginPage();
        VerificationPage verificationPage = loginPage.validLogin(authInfo.getLogin(), authInfo.getPassword());
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode.getCode());

        int balanceBeforeFirst = dashboardPage.getCardBalance(firstCard.getNumber());
        int balanceBeforeSecond = dashboardPage.getCardBalance(secondCard.getNumber());

        dashboardPage.selectCardToTopUp(secondCard.getNumber())
                     .transferMoney(amount, firstCard.getNumber());

        int balanceAfterFirst = dashboardPage.getCardBalance(firstCard.getNumber());
        int balanceAfterSecond = dashboardPage.getCardBalance(secondCard.getNumber());

        assertEquals(balanceBeforeFirst - amount, balanceAfterFirst, 
                "Баланс первой карты (отправителя) должен уменьшиться на сумму перевода");
        assertEquals(balanceBeforeSecond + amount, balanceAfterSecond, 
                "Баланс второй карты (получателя) должен увеличиться на сумму перевода");
    }
}

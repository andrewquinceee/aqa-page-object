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

        // Ищем на дашборде по МАСКИРОВАННОМУ номеру
        int balanceBeforeFirst = dashboardPage.getCardBalance(firstCard.getMaskedNumber());
        int balanceBeforeSecond = dashboardPage.getCardBalance(secondCard.getMaskedNumber());

        // Переводим, указывая ПОЛНЫЙ номер карты отправителя
        dashboardPage.selectCardToTopUp(secondCard.getMaskedNumber())
                     .transferMoney(amount, firstCard.getNumber());

        // Снова проверяем по МАСКИРОВАННОМУ номеру
        int balanceAfterFirst = dashboardPage.getCardBalance(firstCard.getMaskedNumber());
        int balanceAfterSecond = dashboardPage.getCardBalance(secondCard.getMaskedNumber());

        assertEquals(balanceBeforeFirst - amount, balanceAfterFirst, 
                "Баланс первой карты (отправителя) должен уменьшиться на сумму перевода");
        assertEquals(balanceBeforeSecond + amount, balanceAfterSecond, 
                "Баланс второй карты (получателя) должен увеличиться на сумму перевода");
    }
}

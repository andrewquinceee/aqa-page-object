package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        Configuration.baseUrl = "http://localhost:9999";
        Configuration.headless = true;
        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
        
        Selenide.open("/");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.open("/"); // Открываем заново после очистки
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void shouldSeeDashboardAfterLogin() {
        // 1. Логин
        $("[data-test-id='login'] input").setValue("vasya");
        $("[data-test-id='password'] input").setValue("qwerty123");
        $("[data-test-id='action-login']").click();

        // 2. Верификация
        $("[data-test-id='code'] input").setValue("12345");
        $("[data-test-id='action-verify']").click();

        // 3. Проверка: если мы на дашборде, мы должны увидеть хотя бы одну карту и кнопку "Пополнить"
        // Это железобетонная проверка, что приложение загрузилось
        $$(".list__item").shouldHaveSize(2);
        $("[data-test-id='action-deposit']").shouldBe(visible);
        
        // Если дошли сюда - приложение работает, и селекторы верные!
        assertTrue(true); 
    }
}

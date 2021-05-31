package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.web.data.DataGenerator.Registration.generateUser;
import static ru.netology.web.data.DataGenerator.*;

public class AppCardDeliveryReplanTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void TestReplan01() {
        val validUser = generateUser("ru");
        String city = validUser.getCity();
        String date = generateDate(4);
        String phone = validUser.getPhone();
        String name = validUser.getName();

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        closeWindow();

        open("http://localhost:9999");
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        date = generateDate(5);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=\"replan-notification\"] .button__text").click();

        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=\"success-notification\"] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + date));
    }

    @Test
    @DisplayName("Should successful replan meeting to same date")
    void TestReplan02() {
        String city = generateCity();
        String date;
        String phone = generatePhone("ru");
        String name = generateName("ru");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        date = generateDate(4);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        closeWindow();

        open("http://localhost:9999");
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=\"replan-notification\"] .button__text").click();

        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=\"success-notification\"] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + date));
    }

    @Test
    @DisplayName("Should successful plan valid date and replan lover date")
    void TestReplan03() {
        val validUser = generateUser("ru");
        String city = validUser.getCity();
        String date = generateDate(5);
        String phone = validUser.getPhone();
        String name = validUser.getName();

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        closeWindow();

        open("http://localhost:9999");
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        date = generateDate(4);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=\"replan-notification\"] .button__text").click();

        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=\"success-notification\"] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + date));
    }

    @Test
    @DisplayName("Should successful plan valid date and replan past date")
    void TestReplan04() {
        val validUser = generateUser("ru");
        String city = validUser.getCity();
        String date = generateDate(4);
        String phone = validUser.getPhone();
        String name = validUser.getName();

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        closeWindow();

        open("http://localhost:9999");
        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        date = generateDate(-2);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();

        $(("[data-test-id=\"date\"] .input_invalid .input__sub")).shouldBe(visible)
                .shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }
}

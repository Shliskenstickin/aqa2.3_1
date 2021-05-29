package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.LocalDate;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataGenerator.*;

public class AppCardDeliveryPlanTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan meeting")
    void ShouldPlan01() {
        String city = generateCity();
        String date = generateDate(4);
        String phone = generatePhone("ru");
        String name = generateName("ru");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=\"success-notification\"] .notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + date));
    }

    @Test
    @DisplayName("Should Warning If Invalid City")
    void ShouldPlan02() {
        String city = generateCity("en");
        String date = generateDate(4);
        String phone = generatePhone("ru");
        String name = generateName("ru");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(("[data-test-id=\"city\"].input_invalid .input__sub")).shouldBe(visible)
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    @DisplayName("Should warning if invalid name")
    void ShouldPlan03() {
        String city = generateCity();
        String date = generateDate(4);
        String phone = generatePhone("ru");
        String name = generateName("en");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(("[data-test-id=\"name\"].input_invalid .input__sub")).shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    @DisplayName("Should warning if phone not valid")
    void ShouldPlan04() {
        String city = generateCity();
        String date = generateDate(4);
        String phone = "7777";
        String name = generateName("ru");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(("[data-test-id=\"phone\"].input_invalid .input__sub")).shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    @DisplayName("Should warning if phone not enter")
    void ShouldPlan08() {
        String city = generateCity();
        String date = generateDate(4);

        String name = generateName("ru");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(("[data-test-id=\"phone\"].input_invalid .input__sub")).shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    @DisplayName("Should Should warning if checkbox off")
    void ShouldPlan05() {
        String city = generateCity();
        String date = generateDate(4);
        String phone = generatePhone("ru");
        String name = generateName("ru");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $$("button").find(exactText("Запланировать")).click();
        $(("[data-test-id=\"agreement\"].input_invalid")).shouldBe(visible);
    }

    @Test
    @DisplayName("Should warning if past date")
    void ShouldPlan06() {
        String city = generateCity();
        String date = generateDate(-1);
        String phone = generatePhone("ru");
        String name = generateName("ru");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(("[data-test-id=\"date\"] .input_invalid .input__sub")).shouldBe(visible)
                .shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    @DisplayName("Should warning if plan day to day")
    void ShouldPlan07() {
        String city = generateCity();
        String date = generateDate();
        String phone = generatePhone("ru");
        String name = generateName("ru");

        $("[data-test-id=\"city\"] [class=\"input__control\"]").setValue(city);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.CONTROL + "A");
        $("[data-test-id=\"date\"] [class=\"input__control\"]").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] [class=\"input__control\"]").setValue(date);
        $("[data-test-id=\"name\"] [class=\"input__control\"]").setValue(name);
        $("[data-test-id=\"phone\"] [class=\"input__control\"]").setValue(phone);
        $("[data-test-id=\"agreement\"] [class=\"checkbox__box\"]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(("[data-test-id=\"date\"] .input_invalid .input__sub")).shouldBe(visible)
                .shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }
}

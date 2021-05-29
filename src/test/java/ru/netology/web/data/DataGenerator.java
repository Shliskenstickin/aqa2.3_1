package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DataGenerator {

    public static String generateCity() {
        String[] city = new String[]{"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик",
                "Элиста", "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск",
                "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита",
                "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск",
                "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир", "Волгоград", "Вологда",
                "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курган",
                "Курск", "Санкт-Петербург", "Липецк", "Магадан", "Москва", "Мурманск", "Нижний Новгород", "Великий Новгород",
                "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов",
                "Южно-Сахалинск", "Екатеринбург", "Смоленск", "Тамбов", "Тверь", "Томск", "Тула", "Тюмень", "Ульяновск",
                "Челябинск", "Ярославль", "Севастополь"};
        Random r = new Random();
        return city[r.nextInt(city.length)];
    }

    public static String generateCity(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.address().city();
    }

    public static String generateDate() {
        return LocalDate.now().format(ofPattern("dd.MM.yyyy"));
    }

    public static String generateDate(long day) {
        if (day > 0) {
            return LocalDate.now().plusDays(day).format(ofPattern("dd.MM.yyyy"));
        }
        if (day < 0) {
            return LocalDate.now().minusDays(day).format(ofPattern("dd.MM.yyyy"));
        }
        return null;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }
}

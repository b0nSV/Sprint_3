package ru.praktikum_services.qa_scooter.helpers;

import ru.praktikum_services.qa_scooter.helpers.enums.DateEra;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomSequences {

    public static String createRandomUuid() {
        return String.valueOf(UUID.randomUUID());
    }

    public static String createRandomPassword(int length) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String getRandomName() {
        List<String> namesFromNaruto = List.of("Наруто Узумаки", "Саске Учиха", "Орочимару", "Мадара Учиха", "Гаара",
                "Ямато", "Джирайя", "Итачи Учиха", "Цунаде", "Киллер Би");
        Random rnd = new Random();
        return namesFromNaruto.get(rnd.nextInt(namesFromNaruto.size()));
    }

    public static String getDate(DateEra dateEra) {
        Random rnd = new Random();
        switch (dateEra) {
            case DAY_IN_PAST:
                return LocalDate.now().minusDays(rnd.nextInt(14)).toString();
            case DAY_IN_PRESENT:
                return LocalDate.now().toString();
            case DAY_IN_FEATURE:
                return LocalDate.now().plusDays(rnd.nextInt(14)).toString();
        }
        return null;
    }

    public static String getRandomPhoneNumber() {
        int phoneNumberLength = 12;
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(phoneNumberLength);
        sb.append("+7");
        for (int i = 0; i < phoneNumberLength - 2; i++) {
            sb.append(rnd.nextInt(9));
        }
        return sb.toString();
    }

    public static int getRandomRentTime() {
        Random rnd = new Random();
        return rnd.ints(1, 7).findAny().orElseThrow();
    }

    public static String getRandomAddress() {
        List<String> moscowAddresses = List.of("105066, город Москва, Лескова улица, дом 30",
                "109147, город Москва, 1-я Дубровская улица, дом 1А",
                "105066, город Москва, Верхняя Красносельская улица, дом 8, корпус 3",
                "127018, город Москва, Шмитовский проезд, дом 13/6, корпус 10",
                "125040, город Москва, Литовский Бульвар, дом 44",
                "127550, город Москва, Старый Гай улица, дом 8Д",
                "129515, город Москва, Преображенский вал улица, дом 26",
                "115201, город Москва, Бакинская улица, дом 17",
                "125363, город Москва, Исаковского улица, дом 16, корпус 2",
                "123242, город Москва, Зоологическая улица, дом 2",
                "117105, город Москва, Госпитальный вал улица",
                "119049, город Москва, Изюмская улица, дом 37, корпус 2",
                "107014, город Москва, 1-я Новокузьминская улица, дом 11");
        Random rnd = new Random();
        return moscowAddresses.get(rnd.nextInt(moscowAddresses.size()));
    }

    public static String getRandomMetroStation () {
        Random rnd = new Random();
        int metroStationId = rnd.ints(1, 237).findAny().orElseThrow();
        return Integer.toString(metroStationId);
    }
}


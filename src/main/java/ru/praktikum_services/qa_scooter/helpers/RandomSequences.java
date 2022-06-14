package ru.praktikum_services.qa_scooter.helpers;

import com.github.javafaker.Faker;
import ru.praktikum_services.qa_scooter.helpers.enums.DateEra;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class RandomSequences {

    // Русская локаль для случайных значений
    private static final Faker ruFaker = new Faker(new Locale("ru"));

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
        return ruFaker.name().firstName();
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
        return ruFaker.phoneNumber().phoneNumber();
    }

    public static int getRandomRentTime() {
        Random rnd = new Random();
        return rnd.ints(1, 7).findAny().orElseThrow();
    }

    public static String getRandomAddress() {
        return String.format("%s, %s", ruFaker.address().cityName(), ruFaker.address().streetAddress());
    }

    public static String getRandomMetroStationId() {
        Random rnd = new Random();
        int metroStationId = rnd.ints(1, 237).findAny().orElseThrow();
        return Integer.toString(metroStationId);
    }
}


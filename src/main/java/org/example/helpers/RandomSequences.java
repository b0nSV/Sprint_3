package org.example.helpers;

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
}


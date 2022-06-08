package ru.praktikum_services.qa_scooter.helpers;

import ru.praktikum_services.qa_scooter.helpers.enums.UriPath;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Request {
    private final Properties properties = new Properties();

    public String getProperty(UriPath uriPath) {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/common.properties");
            properties.load(fis);
            switch (uriPath) {
                case BASE_URI:
                    return properties.getProperty("ez_scooter_base_uri");
                case BASE_URL:
                    return properties.getProperty("ez_scooter_base_url");
            }
        } catch (IOException e) {
            System.err.println("Отсутствует файл свойств");
            throw new RuntimeException(e);
        }
        return null;
    }

}

package org.example.helpers;

import org.example.helpers.enums.UriPath;

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
                case HOST:
                    return properties.getProperty("ez_scooter_base_uri");
                case BASE_URL:
                    return properties.getProperty("base_url");
                case COURIER_URL:
                    return properties.getProperty("courier_url");
                case ORDERS_URL:
                    return properties.getProperty("orders_url");
            }
        } catch (IOException e) {
            System.err.println("Файл свойств отсутствует!");
            throw new RuntimeException(e);
        }
        return null;
    }

}

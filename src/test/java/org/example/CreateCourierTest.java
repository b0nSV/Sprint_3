package org.example;

import com.google.gson.Gson;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.entities.Courier;
import org.example.entities.CourierResponseBody;
import org.example.helpers.RandomSequences;
import org.example.helpers.Request;
import org.example.helpers.steps.CourierSteps;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.example.helpers.enums.UriPath.*;
import static org.junit.Assert.*;

public class CreateCourierTest {
    //Gson gson;
    Request request = new Request();
    String login;
    String firstName;
    String password;

    @Before
    public void setUp() {
        // gson = new Gson();
        RestAssured.baseURI = request.getProperty(HOST);
        login = RandomSequences.createRandomUuid();
        firstName = RandomSequences.getRandomName();
        password = RandomSequences.createRandomPassword(12);
    }

    @Test
    @DisplayName("При успешном создании курьера возвращается статус код 201")
    public void createCourierAllRequiredParams201() {
        Courier courier = new Courier(login, password, firstName);
        CourierResponseBody courierResponseBody = CourierSteps.doRegister(courier, 201);
        assertEquals(courierResponseBody.isOk(), true);
    }

    @Test
    @DisplayName("400 Bad Request при создании УЗ курьера без пароля")
    public void createCourierNoPassword400() {
        Courier courier = new Courier(login, null, firstName);
        CourierResponseBody courierResponseBody = CourierSteps.doRegister(courier, 400);
        assertEquals("Недостаточно данных для создания учетной записи", courierResponseBody.getMessage());
    }

    @Test
    @DisplayName("400 Bad Request при создании УЗ курьера без логина")
    public void createCourierNoLogin400() {
        Courier courier = new Courier(null, password, firstName);
        CourierResponseBody courierResponseBody = CourierSteps.doRegister(courier, 400);
        assertEquals("Недостаточно данных для создания учетной записи", courierResponseBody.getMessage());
    }

    @Test
    @DisplayName("400 Bad Request при создании УЗ курьера без имени курьера")
    public void createCourierNoFirstName400() {
        Courier courier = new Courier(login, password, null);
        CourierResponseBody courierResponseBody = CourierSteps.doRegister(courier, 400);
        assertEquals("Недостаточно данных для создания учетной записи", courierResponseBody.getMessage());
    }
}

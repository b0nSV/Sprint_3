package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.CourierResponseBody;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;
import ru.praktikum_services.qa_scooter.helpers.Request;
import ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static ru.praktikum_services.qa_scooter.helpers.enums.UriPath.*;
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
    @DisplayName("При успешном создании УЗ курьера возвращается статус код 201")
    public void createCourierAllRequiredParams201() {
        Courier courier = new Courier(login, password, firstName);
        CourierResponseBody courierResponseBody = CourierSteps.doRegister(courier, SC_CREATED);
        assertEquals(courierResponseBody.isOk(), true);
    }

    @Test
    @DisplayName("400 Bad Request при создании УЗ курьера без пароля")
    public void createCourierNoPassword400() {
        Courier courier = new Courier(login, null, firstName);
        CourierResponseBody courierResponseBody = CourierSteps.doRegister(courier, SC_BAD_REQUEST);
        assertEquals("Недостаточно данных для создания учетной записи", courierResponseBody.getMessage());
    }

    @Test
    @DisplayName("400 Bad Request при создании УЗ курьера без логина")
    public void createCourierNoLogin400() {
        Courier courier = new Courier(null, password, firstName);
        CourierResponseBody courierResponseBody = CourierSteps.doRegister(courier, SC_BAD_REQUEST);
        assertEquals("Недостаточно данных для создания учетной записи", courierResponseBody.getMessage());
    }

    @Test
    @DisplayName("400 Bad Request при создании УЗ курьера без имени курьера")
    public void createCourierNoFirstName400() {
        Courier courier = new Courier(login, password, null);
        CourierResponseBody courierResponseBody = CourierSteps.doRegister(courier, SC_BAD_REQUEST);
        assertEquals("Недостаточно данных для создания учетной записи", courierResponseBody.getMessage());
    }
}

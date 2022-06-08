package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.CourierResponse;
import ru.praktikum_services.qa_scooter.entities.ErrorMessageResult;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;
import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.registerCourier;

public class CreateCourierTest {
    String login;
    String firstName;
    String password;
    private static final String NOT_ENOUGH_DATA_CREATE_ACCOUNT_MESSAGE = "Недостаточно данных для создания учетной записи";

    @Before
    public void setUp() {
        login = RandomSequences.createRandomUuid();
        password = RandomSequences.createRandomPassword(12);
        firstName = RandomSequences.getRandomName();
    }

    @Test
    @DisplayName("В теле ответа \"ok\" == true при успешном создании УЗ курьера")
    public void createCourierAllRequiredParamsOkTrue() {
        Courier courier = new Courier(login, password, firstName);
        CourierResponse courierResponse = registerCourier(courier).as(CourierResponse.class);
        assertTrue(courierResponse.isOk());
    }

    @Test
    @DisplayName("Статус код 201 при успешном создании УЗ курьера")
    public void createCourierAllRequiredParams201() {
        Courier courier = new Courier(login, password, firstName);
        Response courierResponse = registerCourier(courier);
        assertEquals(SC_CREATED, courierResponse.statusCode());
    }

    @Test
    @DisplayName("Ошибка при создании УЗ курьера без пароля")
    public void createCourierNoPasswordMessageNoData() {
        Courier courier = new Courier(login, null, firstName);
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(NOT_ENOUGH_DATA_CREATE_ACCOUNT_MESSAGE, courierResponse.getMessage());
    }

    @Test
    @DisplayName("Ошибка при создании УЗ курьера без логина")
    public void createCourierNoLoginMessageNoData() {
        Courier courier = new Courier(null, password, firstName);
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(NOT_ENOUGH_DATA_CREATE_ACCOUNT_MESSAGE, courierResponse.getMessage());
    }

    @Test
    @DisplayName("Ошибка при создании УЗ курьера без имени курьера")
    public void createCourierNoFirstNameMessageNoData() {
        Courier courier = new Courier(login, password, null);
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(NOT_ENOUGH_DATA_CREATE_ACCOUNT_MESSAGE, courierResponse.getMessage());
    }
}

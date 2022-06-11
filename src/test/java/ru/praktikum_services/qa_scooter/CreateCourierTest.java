package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.SimplePositiveResponse;
import ru.praktikum_services.qa_scooter.entities.ErrorMessageResult;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;
import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.registerCourier;
import static ru.praktikum_services.qa_scooter.helpers.steps.BasicSteps.checkStatusCode;

public class CreateCourierTest {
    String login;
    String firstName;
    String password;
    private static final String NOT_ENOUGH_DATA_CREATE_ACCOUNT_MESSAGE = "Недостаточно данных для создания учетной записи";
    private static final String CONFLICT_REGISTRATION_COURIER_MESSAGE = "Этот логин уже используется. Попробуйте другой.";

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
        SimplePositiveResponse courierResponse = registerCourier(courier).as(SimplePositiveResponse.class);
        assertTrue(courierResponse.isOk());
    }

    @Test
    @DisplayName("Статус код 201 при успешном создании УЗ курьера")
    public void createCourierAllRequiredParams201() {
        Courier courier = new Courier(login, password, firstName);
        Response courierResponse = registerCourier(courier);
        checkStatusCode(courierResponse, SC_CREATED);
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

    @Test
    @DisplayName("Ошибка при дублировании запроса создания УЗ курьера")
    public void createCourierDuplicateRequestErrorConflict() {
        Courier courier = new Courier(login, password, firstName);
        registerCourier(courier);
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(CONFLICT_REGISTRATION_COURIER_MESSAGE, courierResponse.getMessage());
    }

    @Test
    @DisplayName("Статус код 409 при дублировании запроса создания УЗ курьера")
    public void createCourierDuplicateRequestReturns409() {
        Courier courier = new Courier(login, password, firstName);
        registerCourier(courier);
        Response courierResponse = registerCourier(courier);
        checkStatusCode(courierResponse, SC_CONFLICT);
    }

    @Test
    @DisplayName("Ошибка при создании курьера с существующим логином")
    public void createCourierWithExistingLoginErrorConflict() {
        Courier courier = new Courier(login, password, firstName);
        registerCourier(courier);
        password = RandomSequences.createRandomPassword(12);
        firstName = RandomSequences.getRandomName();
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(CONFLICT_REGISTRATION_COURIER_MESSAGE,courierResponse.getMessage());
    }

    @Test
    @DisplayName("Статус код 409 при создании курьера с существующим логином")
    public void createCourierWithExistingLoginReturns409() {
        Courier courier = new Courier(login, password, firstName);
        registerCourier(courier);
        password = RandomSequences.createRandomPassword(12);
        firstName = RandomSequences.getRandomName();
        Response courierResponse = registerCourier(courier);
        checkStatusCode(courierResponse, SC_CONFLICT);
    }
}

package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import ru.praktikum_services.qa_scooter.entities.*;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;
import static ru.praktikum_services.qa_scooter.helpers.steps.BasicSteps.checkStatusCode;
import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.*;

@Feature("Регистрация курьера - POST /courier")
public class CreateCourierTest {
    String login;
    String firstName;
    String password;
    Courier courier;
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
        courier = new Courier(login, password, firstName);
        SimplePositiveResponse courierResponse = registerCourier(courier).as(SimplePositiveResponse.class);
        assertTrue(courierResponse.isOk());
    }

    @Test
    @DisplayName("Ошибка при создании УЗ курьера без пароля")
    public void createCourierNoPasswordMessageNoData() {
        courier = new Courier(login, null, firstName);
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(NOT_ENOUGH_DATA_CREATE_ACCOUNT_MESSAGE, courierResponse.getMessage());
    }

    @Test
    @DisplayName("Ошибка при создании УЗ курьера без логина")
    public void createCourierNoLoginMessageNoData() {
        courier = new Courier(null, password, firstName);
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(NOT_ENOUGH_DATA_CREATE_ACCOUNT_MESSAGE, courierResponse.getMessage());
    }

    @Test
    @DisplayName("Ошибка при создании УЗ курьера без имени курьера")
    public void createCourierNoFirstNameMessageNoData() {
        courier = new Courier(login, password, null);
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(NOT_ENOUGH_DATA_CREATE_ACCOUNT_MESSAGE, courierResponse.getMessage());
    }

    @Test
    @DisplayName("Ошибка при дублировании запроса создания УЗ курьера")
    public void createCourierDuplicateRequestErrorConflict() {
        courier = new Courier(login, password, firstName);
        registerCourier(courier);
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(CONFLICT_REGISTRATION_COURIER_MESSAGE, courierResponse.getMessage());
    }

    @Test
    @DisplayName("Статус код 409 при дублировании запроса создания УЗ курьера")
    public void createCourierDuplicateRequestReturns409() {
        courier = new Courier(login, password, firstName);
        registerCourier(courier);
        Response courierResponse = registerCourier(courier);
        checkStatusCode(courierResponse, SC_CONFLICT);
    }

    @Test
    @DisplayName("Ошибка при создании курьера с существующим логином")
    public void createCourierWithExistingLoginErrorConflict() {
        courier = new Courier(login, password, firstName);
        registerCourier(courier);
        password = RandomSequences.createRandomPassword(12);
        firstName = RandomSequences.getRandomName();
        ErrorMessageResult courierResponse = registerCourier(courier).as(ErrorMessageResult.class);
        assertEquals(CONFLICT_REGISTRATION_COURIER_MESSAGE, courierResponse.getMessage());
    }

    @Test
    @DisplayName("Статус код 409 при создании курьера с существующим логином")
    public void createCourierWithExistingLoginReturns409() {
        courier = new Courier(login, password, firstName);
        registerCourier(courier);
        password = RandomSequences.createRandomPassword(12);
        firstName = RandomSequences.getRandomName();
        Response courierResponse = registerCourier(courier);
        checkStatusCode(courierResponse, SC_CONFLICT);
    }

    @After
    public void clear() {
        deleteCourier(courier.getLogin(), courier.getPassword());
    }
}

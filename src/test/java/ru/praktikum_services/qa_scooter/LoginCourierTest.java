package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.CourierCredentials;
import ru.praktikum_services.qa_scooter.entities.ErrorMessageResult;
import ru.praktikum_services.qa_scooter.entities.LoginCourierResponse;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;

import static ru.praktikum_services.qa_scooter.helpers.steps.BasicSteps.checkStatusCode;
import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;
import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.*;

@Feature("Логин курьера - POST /courier/login")
public class LoginCourierTest {
    Courier randomCourier;
    private static final String NOT_FOUND_COURIER_CREDENTIALS_MESSAGE = "Учетная запись не найдена";

    @Before
    public void before() {
        randomCourier = registerRandomCourier();
    }

    @Test
    @DisplayName("Вход по существующей паре логин/пароль курьера возвращает его ID")
    public void loginCourierWithExistingCredentialsReturnsId() {
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin(), randomCourier.getPassword());
        LoginCourierResponse loginCourierResponse = loginCourier(courierCredentials).as(LoginCourierResponse.class);
        assertNotNull(loginCourierResponse.getId());
    }

    @Test
    @DisplayName("Ошибка при входе c несуществующим логином и существующим паролем")
    public void loginCourierNotExistingLoginReturnsError() {
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin() + "_WrongLogin",
                randomCourier.getPassword());
        ErrorMessageResult loginCourierResponse = loginCourier(courierCredentials).as(ErrorMessageResult.class);
        assertEquals(NOT_FOUND_COURIER_CREDENTIALS_MESSAGE, loginCourierResponse.getMessage());
    }

    @Test
    @DisplayName("Статус код 404 при входе c несуществующим логином и существующим паролем")
    public void loginCourierNotExistingLoginReturns404() {
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin() + "_WrongLogin",
                randomCourier.getPassword());
        Response loginCourierResponse = loginCourier(courierCredentials);
        checkStatusCode(loginCourierResponse, SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Ошибка при входе c неверным паролем для логина")
    public void loginCourierNotValidPasswordReturnsError() {
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin(),
                randomCourier.getPassword() + RandomSequences.createRandomPassword(1));
        ErrorMessageResult loginCourierResponse = loginCourier(courierCredentials).as(ErrorMessageResult.class);
        assertEquals(NOT_FOUND_COURIER_CREDENTIALS_MESSAGE, loginCourierResponse.getMessage());
    }

    @Test
    @DisplayName("Статус код 404 при входе c неверным паролем для логина")
    public void loginCourierNotValidPasswordReturns404() {
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin(),
                randomCourier.getPassword() + RandomSequences.createRandomPassword(1));
        Response loginCourierResponse = loginCourier(courierCredentials);
        checkStatusCode(loginCourierResponse, SC_NOT_FOUND);
    }

    @After
    public void clear() {
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin(), randomCourier.getPassword());
        Integer courierId = loginCourier(courierCredentials).as(LoginCourierResponse.class).getId();
        deleteCourier(courierId.toString());
    }
}

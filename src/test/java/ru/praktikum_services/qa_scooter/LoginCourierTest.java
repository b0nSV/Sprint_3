package ru.praktikum_services.qa_scooter;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.CourierCredentials;
import ru.praktikum_services.qa_scooter.entities.ErrorMessageResult;
import ru.praktikum_services.qa_scooter.entities.LoginCourierResponse;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;

import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.registerRandomCourier;
import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.loginCourier;
import static ru.praktikum_services.qa_scooter.helpers.steps.BasicSteps.checkStatusCode;
import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;

public class LoginCourierTest {
    private static final String NOT_FOUND_COURIER_CREDENTIALS_MESSAGE = "Учетная запись не найдена";

    @Test
    @DisplayName("Вход по существующей паре логин/пароль курьера возвращает его ID")
    public void loginCourierWithExistingCredentialsReturnsId() {
        Courier randomCourier = registerRandomCourier();
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin(), randomCourier.getPassword());
        LoginCourierResponse loginCourierResponse = loginCourier(courierCredentials).as(LoginCourierResponse.class);
        assertNotNull(loginCourierResponse.getId());
    }

    @Test
    @DisplayName("Ошибка при входе c несуществующим логином и существующим паролем")
    public void loginCourierNotExistingLoginReturnsError() {
        Courier randomCourier = registerRandomCourier();
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin()+ "_WrongLogin",
                randomCourier.getPassword());
        ErrorMessageResult loginCourierResponse = loginCourier(courierCredentials).as(ErrorMessageResult.class);
        assertEquals(NOT_FOUND_COURIER_CREDENTIALS_MESSAGE, loginCourierResponse.getMessage());
    }

    @Test
    @DisplayName("Статус код 404 при входе c несуществующим логином и существующим паролем")
    public void loginCourierNotExistingLoginReturns404() {
        Courier randomCourier = registerRandomCourier();
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin()+ "_WrongLogin",
                randomCourier.getPassword());
        Response loginCourierResponse = loginCourier(courierCredentials);
        checkStatusCode(loginCourierResponse, SC_NOT_FOUND);
    }

    @Test
    @DisplayName("Ошибка при входе c неверным паролем для логина")
    public void loginCourierNotValidPasswordReturnsError() {
        Courier randomCourier = registerRandomCourier();
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin(),
                randomCourier.getPassword() + RandomSequences.createRandomPassword(1));
        ErrorMessageResult loginCourierResponse = loginCourier(courierCredentials).as(ErrorMessageResult.class);
        assertEquals(NOT_FOUND_COURIER_CREDENTIALS_MESSAGE, loginCourierResponse.getMessage());
    }

    @Test
    @DisplayName("Статус код 404 при входе c неверным паролем для логина")
    public void loginCourierNotValidPasswordReturns404() {
        Courier randomCourier = registerRandomCourier();
        CourierCredentials courierCredentials = new CourierCredentials(randomCourier.getLogin(),
                randomCourier.getPassword() + RandomSequences.createRandomPassword(1));
        Response loginCourierResponse = loginCourier(courierCredentials);
        checkStatusCode(loginCourierResponse, SC_NOT_FOUND);
    }
}

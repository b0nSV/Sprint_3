package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.CourierCredentials;
import ru.praktikum_services.qa_scooter.entities.ErrorMessageResult;
import ru.praktikum_services.qa_scooter.entities.LoginCourierResponse;

import static org.apache.http.HttpStatus.*;
import static ru.praktikum_services.qa_scooter.helpers.steps.BasicSteps.checkStatusCode;
import static org.junit.Assert.*;
import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.*;

@Feature("Логин курьера - POST /courier/login")
@Story("Для входа под УЗ курьера нужно передать все обязательные атрибуты")
@RunWith(Parameterized.class)
public class LoginCourierRequiredArgsTest {
    private final boolean isLogin;
    private final boolean isPassword;
    private final int statusCode;

    public LoginCourierRequiredArgsTest(boolean isLogin, boolean isPassword, int statusCode) {
        this.isLogin = isLogin;
        this.isPassword = isPassword;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters(name = " theres login -> {0} | theres password -> {1} | response status code {2} ")
    public static Object[][] getLoginData() {
        return new Object[][]{
                // TODO раскомментировать перед сдачей
                {false, true, SC_BAD_REQUEST},
//                {true, false, SC_BAD_REQUEST},
//                {false, false, SC_BAD_REQUEST},
                {true, true, SC_OK},
        };
    }

    @Test
    public void loginCourierRequiredArgsTest() {
        CourierCredentials courierCredentials;
        Courier randomCourier = registerRandomCourier();
        if (!isLogin && isPassword) {
            courierCredentials = new CourierCredentials(null, randomCourier.getPassword());
        } else if (isLogin && !isPassword) {
            courierCredentials = new CourierCredentials(randomCourier.getLogin(), null);
        } else if (!isLogin) {
            courierCredentials = new CourierCredentials(null, null);
        } else {
            courierCredentials = new CourierCredentials(randomCourier.getLogin(), randomCourier.getPassword());
        }
        Response loginCourierResponse = loginCourier(courierCredentials);
        if (statusCode == SC_BAD_REQUEST){
            checkStatusCode(loginCourierResponse, statusCode);
            assertEquals("Недостаточно данных для входа", loginCourierResponse.as(ErrorMessageResult.class).getMessage());
        } else {
            checkStatusCode(loginCourierResponse, statusCode);
        }
        CourierCredentials courierCredentialsToDelete = new CourierCredentials(randomCourier.getLogin(), randomCourier.getPassword());
        Integer courierId =  loginCourier(courierCredentialsToDelete).as(LoginCourierResponse.class).getId();
        deleteCourier(courierId.toString());
    }
}

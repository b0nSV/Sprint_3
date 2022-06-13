package ru.praktikum_services.qa_scooter.helpers.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.CourierCredentials;
import ru.praktikum_services.qa_scooter.entities.LoginCourierResponse;
import ru.praktikum_services.qa_scooter.helpers.BaseApiSpecs;

import static io.restassured.RestAssured.*;
import static org.apache.http.HttpStatus.SC_CREATED;

public class CourierSteps extends BaseApiSpecs {

    private static final String CREATE_COURIER_URL = "/courier";
    private static final String LOGIN_COURIER_URL = "/courier/login";
    private static final String DELETE_COURIER_URL = "/courier/";

    @Step("Регистрация курьера") // Type {courier.login} / {courier.password} / {courier.firstName}
    public static Response registerCourier(Courier courier) {
        return given()
                .spec(getPostReqSpec())
                .and()
                .body(courier)
                .when()
                .post(BASE_URI + BASE_URL + CREATE_COURIER_URL);
    }

    @Step("Регистрация случайного курьера") // Type {courier.login} / {courier.password} / {courier.firstName}
    public static Courier registerRandomCourier() {
        Courier randomCourier = Courier.getRandomCourier();
        given()
                .spec(getPostReqSpec())
                .and()
                .body(randomCourier)
                .when()
                .post(BASE_URI + BASE_URL + CREATE_COURIER_URL).then().statusCode(SC_CREATED);
        return randomCourier;
    }

    @Step("Выполнение входа курьером в систему") // Type {courierCredentials.login} / {courierCredentials.password}
    public static Response loginCourier(CourierCredentials courierCredentials) {
        return given()
                .spec(getPostReqSpec())
                .and()
                .body(courierCredentials)
                .when()
                .post(BASE_URI + BASE_URL + LOGIN_COURIER_URL);
    }

    @Step("Удалить учетную запись курьера")
    public static Response deleteCourier(String courierId) {
        return given()
                .spec(getDeleteReqSpec())
                .when()
                .delete(BASE_URI + BASE_URL + DELETE_COURIER_URL + courierId);
    }

    @Step("Удалить учетную запись курьера")
    public static void deleteCourier(String login, String password) {
        if (login == null || password == null) {
            System.out.println("Курьер не был создан");
        } else {
            CourierCredentials courierCredentials = new CourierCredentials(login, password);
            Integer courierId = loginCourier(courierCredentials).as(LoginCourierResponse.class).getId();
            deleteCourier(courierId.toString());
        }
    }
}

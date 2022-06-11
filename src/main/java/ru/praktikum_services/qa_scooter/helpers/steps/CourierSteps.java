package ru.praktikum_services.qa_scooter.helpers.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.CourierCredentials;
import ru.praktikum_services.qa_scooter.helpers.BaseApiSpecs;

import static io.restassured.RestAssured.*;
import static org.apache.http.HttpStatus.SC_CREATED;

public class CourierSteps extends BaseApiSpecs {

    private static final String CREATE_COURIER_URL = "/courier";

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

    @Step("Выполнение входа в систему курьером") // Type {courierCredentials.login} / {courierCredentials.password}
    public static Response loginCourier(CourierCredentials courierCredentials) {
        return given()
                .spec(getPostReqSpec())
                .and()
                .body(courierCredentials)
                .when()
                .post(BASE_URI + BASE_URL + "/courier/login");
    }
}

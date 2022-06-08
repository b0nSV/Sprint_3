package ru.praktikum_services.qa_scooter.helpers.steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.helpers.BaseApiSpecs;

import static io.restassured.RestAssured.*;

public class CourierSteps extends BaseApiSpecs {

    @Step("Регистрация курьера") // Type {courier.login} / {courier.password} / {courier.firstName}
    public static Response registerCourier(Courier courier) {
        return given()
                .spec(getPostReqSpec())
                .and()
                .body(courier)
                .when()
                .post(BASE_URI+ BASE_URL + "/courier");
    }
}

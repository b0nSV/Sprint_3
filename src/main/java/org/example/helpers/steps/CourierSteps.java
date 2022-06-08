package org.example.helpers.steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.entities.Courier;
import org.example.entities.CourierResponseBody;
import org.example.helpers.Request;

import static io.restassured.RestAssured.*;
import static org.example.helpers.enums.UriPath.*;

public class CourierSteps {

    static Request request = new Request();
    static String baseURL = request.getProperty(BASE_URL);
    static String courierUrl = request.getProperty(COURIER_URL);

    @Step("Регистрация курьера") // Type {courier.login} / {courier.password} / {courier.firstName}
    @Attachment(value = "Тело ответа")
    public static CourierResponseBody doRegister(Courier courier, int statusCode) {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(baseURL + courierUrl);
        response.then().statusCode(statusCode);
        return response.as(CourierResponseBody.class);
    }
}

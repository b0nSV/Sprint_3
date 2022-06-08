package ru.praktikum_services.qa_scooter.helpers.steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.entities.CourierResponseBody;
import ru.praktikum_services.qa_scooter.helpers.Request;

import static io.restassured.RestAssured.*;
import static ru.praktikum_services.qa_scooter.helpers.enums.UriPath.*;

public class CourierSteps {

    static Request request = new Request();
    static String baseURL = request.getProperty(BASE_URL);
    static String courierUrl = request.getProperty(COURIER_URL);

    @Step("Регистрация курьера") // Type {courier.login} / {courier.password} / {courier.firstName}
    @Attachment(value = "Тело ответа")
    public static CourierResponseBody doRegister(Courier courier, int statusCode) {
        Response response = given()
                .contentType(ContentType.JSON)
                .and()
                .body(courier)
                .when()
                .post(baseURL + courierUrl);
        response.then().statusCode(statusCode);
        return response.as(CourierResponseBody.class);
    }
}

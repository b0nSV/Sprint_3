package ru.praktikum_services.qa_scooter.helpers.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.entities.Order;
import ru.praktikum_services.qa_scooter.helpers.BaseApiSpecs;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class OrderSteps extends BaseApiSpecs {
    private static final String CREATE_ORDER_URL = "/orders";

    @Step("Создание заказа")
    public static Response createOrder(Order order) {
        return given()
                .spec(getPostReqSpec())
                .and()
                .body(order)
                .when()
                .post(BASE_URI + BASE_URL + CREATE_ORDER_URL);
    }

    @Step("Получения списка заказов")
    public static Response getOrderList(HashMap<String, String> params) {
        return given()
                .queryParams(params)
                .spec(getGetReqSpec())
                .when()
                .get(BASE_URI + BASE_URL + CREATE_ORDER_URL);
    }
}

package ru.praktikum_services.qa_scooter.helpers.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum_services.qa_scooter.entities.Order;
import ru.praktikum_services.qa_scooter.entities.OrderTrack;
import ru.praktikum_services.qa_scooter.helpers.BaseApiSpecs;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class OrderSteps extends BaseApiSpecs {
    private static final String CREATE_ORDER_URL = "/orders";
    private static final String CANCEL_ORDER_URL = "/orders/cancel";

    @Step("Создать заказ")
    public static Response createOrder(Order order) {
        return given()
                .spec(getPostReqSpec())
                .and()
                .body(order)
                .when()
                .post(BASE_URI + BASE_URL + CREATE_ORDER_URL);
    }

    @Step("Получить список заказов")
    public static Response getOrderList(HashMap<String, String> queryParams) {
        return given()
                .queryParams(queryParams)
                .spec(getGetReqSpec())
                .when()
                .get(BASE_URI + BASE_URL + CREATE_ORDER_URL);
    }

    @Step("Отменить заказ по его трек номеру")
    public static void cancelOrderByTrack(OrderTrack orderTrack) {
        given()
                .spec(getPostReqSpec())
                .and()
                .body(orderTrack)
                .when()
                .put(BASE_URI + BASE_URL + CANCEL_ORDER_URL);
    }
}

package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.entities.Order;
import ru.praktikum_services.qa_scooter.entities.OrderList;
import ru.praktikum_services.qa_scooter.entities.OrderTrack;

import java.util.HashMap;

import static ru.praktikum_services.qa_scooter.helpers.steps.OrderSteps.*;
import static org.junit.Assert.*;

@Feature("Получение списка заказов - POST /orders/list")
public class GetOrderListTest {
    OrderTrack orderTrack;

    @Test
    @DisplayName("Можно получить список заказов выполнив запрос без необязательных параметров")
    public void getOrderListNoParamsReturnsOrders(){
        Order randomOrder = Order.getRandomRequiredArgsOrder();
        orderTrack = createOrder(randomOrder).as(OrderTrack.class);
        OrderList orderList = getOrderList(new HashMap<>()).as(OrderList.class);
        assertFalse("Список заказов должен содержит хотя бы одно значение", orderList.getOrders().isEmpty());
    }

/* for debug test^
    @Test
    @DisplayName("Массив orders непустой для станции 221")
    public void getOrderListNearest2211MetroFail(){
        Order randomOrder = Order.getRandomRequiredArgsOrder();
        orderTrack = createOrder(randomOrder).as(OrderTrack.class);
        HashMap<String, String> queryparams = new HashMap<>();
        queryparams.put("nearestStation", "[\"221\"]");
        OrderList orderList = getOrderList(queryparams).as(OrderList.class);
        assertFalse("Список заказов содержит хотя бы одно значение",orderList.getOrders().isEmpty());
    }*/

    @After
    public void clear(){
        cancelOrderByTrack(orderTrack);
    }
}

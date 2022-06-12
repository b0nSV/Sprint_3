package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.entities.Order;
import ru.praktikum_services.qa_scooter.entities.OrderTrack;

import java.util.List;

import static ru.praktikum_services.qa_scooter.helpers.steps.OrderSteps.*;
import static org.junit.Assert.*;

@Feature("Создание заказа - POST /orders")
@Story("Пользователь может выбрать цвет самоката")
@RunWith(Parameterized.class)
public class ChoseScooterColorInOrderTest {
    private final List<String> color;

    public ChoseScooterColorInOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "Scooter color: {0}")
    public static Object[][] getScooterColorData() {
        return new Object[][] {
                {List.of("BLACK", "GRAY")},
                {List.of("GRAY")},
                {List.of("BLACK")},
                {List.of()},
        };
    }

    @Test
    public void userCanChooseScooterColorTest(){
        Order order = Order.getRandomRequiredArgsOrder();
        order.setColor(color);
        OrderTrack orderTrack = createOrder(order).as(OrderTrack.class);
        assertNotNull(orderTrack.getTrack());
        // Удаление созданного заказа
        cancelOrderByTrack(orderTrack);
    }
}

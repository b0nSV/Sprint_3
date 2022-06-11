package ru.praktikum_services.qa_scooter;

import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;


import static org.apache.http.HttpStatus.*;
import static ru.praktikum_services.qa_scooter.helpers.steps.BasicSteps.checkStatusCode;
import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.registerCourier;

@Feature("Регистрация курьера - POST /courier")
@RunWith(Parameterized.class)
public class CreateCourierRequiredArgsTest {
    private final String login;
    private final String password;
    private final String firstName;
    private final int statusCode;


    public CreateCourierRequiredArgsTest(String login, String password, String firstName, int statusCode) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters(name = "login = {0} | password = {1} | firstName = {2} | requiredStatusCode = {3}")
    public static Object[][] getCourierData() {
        return new Object[][] {
                {RandomSequences.createRandomUuid(), RandomSequences.createRandomPassword(12), null, SC_BAD_REQUEST},
                {RandomSequences.createRandomUuid(), null, RandomSequences.getRandomName(),SC_BAD_REQUEST},
                {null, RandomSequences.createRandomPassword(12), RandomSequences.getRandomName(), SC_BAD_REQUEST},
                {null, null, RandomSequences.getRandomName(), SC_BAD_REQUEST},
                {null, null, null, SC_BAD_REQUEST},
                {RandomSequences.createRandomUuid(), RandomSequences.createRandomPassword(12),RandomSequences.getRandomName(), SC_CREATED},
        };
    }

    @Test
    public void registerCourierRequiredArgs() {
        Courier courier = new Courier(login, password, firstName);
        Response response = registerCourier(courier);
        checkStatusCode(response, statusCode);
    }
}

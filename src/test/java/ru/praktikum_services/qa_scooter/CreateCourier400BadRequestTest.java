package ru.praktikum_services.qa_scooter;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.entities.Courier;
import ru.praktikum_services.qa_scooter.helpers.RandomSequences;


import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;
import static ru.praktikum_services.qa_scooter.helpers.steps.BasicSteps.checkStatusCode;
import static ru.praktikum_services.qa_scooter.helpers.steps.CourierSteps.registerCourier;

@RunWith(Parameterized.class)
public class CreateCourier400BadRequestTest {
    private final String login;
    private final String password;
    private final String firstName;


    public CreateCourier400BadRequestTest(String login, String password, String firstName, String missingParameter) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    @Parameterized.Parameters(name = "Запрос без: {3}")
    public static Object[][] getCourierData() {
        return new Object[][] {
                {RandomSequences.createRandomUuid(), RandomSequences.createRandomPassword(12), null, "firstName"},
                {RandomSequences.createRandomUuid(), null, RandomSequences.getRandomName(),"password"},
                {null, RandomSequences.createRandomPassword(12), RandomSequences.getRandomName(), "login"},
                {null, null, RandomSequences.getRandomName(), "login, password"},
                {null, null, null, "login, password, firstName"},
        };
    }

    @Test
    public void registerWithoutRequiredParamsReturns400() {
        Courier courier = new Courier(login, password, firstName);
        Response response = registerCourier(courier);
        checkStatusCode(response, SC_BAD_REQUEST);
    }
}

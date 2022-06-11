package ru.praktikum_services.qa_scooter.helpers.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.apache.http.HttpStatus.*;

public class BasicSteps {

    @Step
    public static void checkStatusCode(Response response, int statusCode) {
        // List<Integer> positiveStatusCodes = List.of(SC_CREATED, SC_OK);
        if (statusCode < 400) {
            assertEquals(statusCode, response.statusCode());
        }
        else {
            assertEquals(statusCode, response.statusCode());
            assertEquals(statusCode, response.getBody().jsonPath().getInt("code"));
        }
    }
}
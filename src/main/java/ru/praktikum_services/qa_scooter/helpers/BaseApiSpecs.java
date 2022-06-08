package ru.praktikum_services.qa_scooter.helpers;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.praktikum_services.qa_scooter.helpers.enums.UriPath;

public class BaseApiSpecs {

    private static final Request request = new Request();
    public static final String BASE_URI = request.getProperty(UriPath.BASE_URI);
    public static final String BASE_URL =  request.getProperty(UriPath.BASE_URL);
    public static RequestSpecification getPostReqSpec() {
        return new RequestSpecBuilder().log(LogDetail.ALL)
                .setContentType(ContentType.JSON).build().filter(new AllureRestAssured());
    }
}

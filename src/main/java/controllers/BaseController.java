package controllers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.HashMap;


public class BaseController {

    protected static final String BASE_URL = System.getProperty("api.url");
    protected static final String BEARER_TOKEN = System.getProperty("api.token");

    protected RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .addHeader("Authorization", "Bearer " + BEARER_TOKEN)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    protected ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    protected Response makeGetRequest(String path) {
        return RestAssured.given()
                .spec(requestSpecification)
                .when()
                .get(path)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makeGetRequest(HashMap<String, String> queryParams, String path) {
        return RestAssured.given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .when()
                .get(path)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makePostRequest(HashMap<String, String> queryParams, String path) {
        return RestAssured.given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .when()
                .post(path)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makePostRequest(String path, String body) {
        return RestAssured.given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .post(path)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makePostRequest(HashMap<String, String> queryParams, String path, String body) {
        return RestAssured.given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .body(body)
                .when()
                .post(path)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    protected Response makeDeleteRequest(HashMap<String, String> queryParams, String path) {
        return RestAssured.given()
                .spec(requestSpecification)
                .queryParams(queryParams)
                .when()
                .delete(path)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }
}


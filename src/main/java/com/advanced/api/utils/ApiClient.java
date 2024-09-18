package com.advanced.api.utils;

import com.advanced.api.config.Config;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class ApiClient {

    private static final String BASE_URL = Config.getBaseUrl();

    public static Response get(String endpoint) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .get(endpoint);
    }

    public static Response post(String endpoint, String body, String contentType) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .body(body)
                .when()
                .post(endpoint);
    }

    public static Response put(String endpoint, String body, String contentType) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .contentType(contentType)
                .body(body)
                .when()
                .put(endpoint);
    }

    public static Response delete(String endpoint) {
        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .when()
                .delete(endpoint);
    }
}

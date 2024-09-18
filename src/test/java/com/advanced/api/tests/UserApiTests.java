package com.advanced.api.tests;

import com.advanced.api.config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;


public class UserApiTests extends BaseTest{

    @Test
    public void testGetUser() {
        String baseUrl = Config.getBaseUrl();
        String endpoint = "/api/users/2";
        String fullUrl = baseUrl + endpoint;

        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .when()
                .get(endpoint);

// Print the complete URL
        System.out.println("Full URL: " + fullUrl);

// Print the response status code and body
        response.then().statusCode(200);
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
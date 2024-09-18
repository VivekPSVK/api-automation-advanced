package com.advanced.api.validations;

import io.restassured.response.Response;
import static org.testng.AssertJUnit.assertEquals;

public class UserValidations {
    public static void validateUserResponse(Response response) {
        assertEquals(response.statusCode(), 200);
        // Add more validation logic as needed
    }
}

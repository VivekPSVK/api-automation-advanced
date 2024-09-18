package com.advanced.api.validations;



import io.restassured.response.Response;
import static org.testng.Assert.*;

public class ResourceValidations {

    public static void validateProductResponse(Response response) {
        assertEquals(response.statusCode(), 200);
        // Add more validation logic as needed
    }
}

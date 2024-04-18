package com.ministryoftesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.response.Response;
import com.ministryoftesting.api.AuthPayload;
import com.ministryoftesting.api.TimesheetManagerApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TimesheetManagerApplication.class)
@ActiveProfiles("dev")

public class LoginApiTest {

	@Test
	public void testCheckLoginReturnsPositiveResult(){
		AuthPayload authPayload = new AuthPayload("admin@test.com", "password123");

		Response response = given()
				.body(authPayload)
				.contentType("application/json")
				.post("http://localhost:8080/v1/auth/login");

		assertEquals(200, response.getStatusCode());
	}

}

package com.requests;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

import com.ministryoftesting.models.auth.Login;
import com.ministryoftesting.models.auth.Token;

public class AuthRequests {

	public static Response postLogin( Login login) {
		return given()
				.body(login)
				.contentType("application/json")
				.post("/v1/auth/login");
	}

	public static Response postValidate( Token token) {
		return given()
				.body(token)
				.contentType("application/json")
				.post("/v1/auth/validate");
	}

	public static Response postLogout(Token token) {
		return given()
				.body(token)
				.contentType("application/json")
				.post("/v1/auth/logout");
	}
}

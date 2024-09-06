package com.ministryoftesting.models.user;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.response.Response;

import com.ministryoftesting.models.auth.Credentials;

public class DataBuilder {

	private String token;
	private List<TimesheetCredential> getStandardUser() {
		return given()
				.header("Authorization", "Bearer " + token)
				.get("/v1/user")
				.jsonPath()
				.getList("", TimesheetCredential.class);
	}

	private TimesheetCredential createStandardUser(String userType) {
		Response response = given()
				.body(new TimesheetCredential("Jon", "test@email.com", "password123", userType))
				.contentType("application/json")
				.header("Authorization", "Bearer " + token)
				.post("/v1/user");

		return response.as(TimesheetCredential.class);
	}

	public DataBuilder() {
		Credentials credentials = given()
				.body("{\"email\":\"admin@test.com\",\"password\":\"password123\"}")
				.contentType("application/json")
				.post("/v1/auth/login")
				.as(Credentials.class);

		token = credentials.getToken();
	}

	public TimesheetCredential getUserCredentials(String userType) {
		List<TimesheetCredential> credentials = getStandardUser();

		for(TimesheetCredential credential : credentials){
			if(credential.getRole().equals(userType)){
				return credential;
			}
		}

		TimesheetCredential createdCredentials = createStandardUser(userType);

		return createdCredentials;
	}

}

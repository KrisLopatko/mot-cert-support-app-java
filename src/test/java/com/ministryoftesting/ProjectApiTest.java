package com.ministryoftesting;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

import static io.restassured.RestAssured.given;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.output.WriterOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.output.WriterOutputStream.Builder;
//import org.apache.commons.io.build.AbstractStreamBuilder;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import com.ministryoftesting.api.TimesheetManagerApplication;
import com.ministryoftesting.models.auth.Credentials;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TimesheetManagerApplication.class)
@ActiveProfiles("dev")

public class ProjectApiTest {

	@BeforeEach
	public void setupLogging( TestInfo testInfo) throws IOException {
		FileWriter fileWriter = new FileWriter("target/" + testInfo.getDisplayName() + ".log");
		//FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

		WriterOutputStream writerOutputStream = WriterOutputStream.builder()
				.setWriter(fileWriter)
				.get();

		System.setOut(new PrintStream(writerOutputStream));

//		WriterOutputStream str = new WriterOutputStream.Builder(fileWriter)
//				.setPath(path)
//				.setBufferSize(8192)
//				.setCharset( StandardCharsets.UTF_8)
//				.setWriteImmediately(false)
//				.get();

		//PrintStream printStream = new PrintStream( str(fileWriter), true);
		//PrintStream printStream = new PrintStream( WriterOutputStream.Builder builder(fileWriter), true);

		//RestAssured.filters(new RequestLoggingFilter(printStream), new ResponseLoggingFilter(printStream));
	}

	@Test
	@DisplayName("Test getting a project")
	public void testGettingProject2(){
		Credentials credentials = given()
				.body("{\"email\":\"admin@test.com\",\"password\":\"password123\"}")
				.contentType("application/json")
				.post("/v1/auth/login")
				.as(Credentials.class);

		String token = credentials.getToken();

		Response response = given()
				.header("Authorization", "Bearer " + token)
				.get("/v1/project/2");

		Approvals.verify(response.getBody().prettyPrint());

		}


	@AfterEach
	public void addLogToAllure(TestInfo testInfo) throws IOException {
		String log = new String( Files.readAllBytes( Paths.get("target/" + testInfo.getDisplayName() + ".log")));

		Allure.addAttachment(testInfo.getDisplayName(), log);
	}

}

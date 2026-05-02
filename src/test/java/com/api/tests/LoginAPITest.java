package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	@Test
	public void loginAPITest() throws IOException {
		UserCredentials userCreds = new UserCredentials("iamfd", "password");
		given().spec(SpecUtil.requestSpec(userCreds))
		.and()
		.when()
		.post("login")
		.then()
		.spec(SpecUtil.responseSpe_OK())
				.and().body("message", Matchers.equalTo("Success")).body("data.token", Matchers.notNullValue(null))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}

}

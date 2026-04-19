package com.api.tests;

import static com.api.utils.ConfigManagerOLD.*;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManagerOLD;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public void verifyCountAPI() throws IOException {

		given().baseUri(ConfigManagerOLD.getProperty("BASE_URI")).and()
				.header("Authorization", AuthTokenProvider.getToken(Role.FD)).accept(ContentType.JSON).and().log().uri()
				.log().method().log().headers().when().get("dashboard/count").then().log().all().statusCode(200)
				.time(Matchers.lessThan(1500L)).body("message", Matchers.equalTo("Success"))
				.body("data", Matchers.notNullValue()).body("data.size()", Matchers.equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body("data.label", Matchers.containsInAnyOrder("Pending for delivery","Created today","Pending for FST assignment"))
				.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema//CountAPIResponseSchema.json"));

	}

	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		given().baseUri(ConfigManagerOLD.getProperty("BASE_URI")).and().accept(ContentType.JSON).and().log().uri().log()
				.method().log().headers().when().get("dashboard/count").then().log().all().statusCode(401);
	}

}

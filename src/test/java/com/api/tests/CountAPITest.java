package com.api.tests;

import static com.api.utils.ConfigManagerOLD.*;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManagerOLD;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public void verifyCountAPI() throws IOException {

		given()
		        .spec(SpecUtil.requestSpecificationWithAuth(Role.FD))
		        .and()
				.when().get("dashboard/count")
				.then()
				.spec(SpecUtil.responseSpe_OK())
				.body("message", Matchers.equalTo("Success"))
				.body("data", Matchers.notNullValue()).body("data.size()", Matchers.equalTo(3))
				.body("data.count", Matchers.everyItem(Matchers.greaterThanOrEqualTo(0)))
				.body("data.label", Matchers.everyItem(Matchers.not(Matchers.blankOrNullString())))
				.body("data.label", Matchers.containsInAnyOrder("Pending for delivery","Created today","Pending for FST assignment"))
				.body("data.key", Matchers.containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema//CountAPIResponseSchema.json"));

	}

	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		given().spec(SpecUtil.requestSpec())
		.when().get("dashboard/count")
		.then()
		.spec(SpecUtil.responseSpe_Text(401));
	}

}

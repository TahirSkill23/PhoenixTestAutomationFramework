package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	@Test
	public void userdetailsAPItest() throws IOException {
		given().
		        spec(SpecUtil.requestSpecificationWithAuth(Role.FD))
		        .when()
		        .get("userdetails")
		        .then()
		        .spec(SpecUtil.responseSpe_OK())
		        .and()
		        .body("message",Matchers.equalTo("Success"))
		        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/userDetailsResponseSchema.json"));
		     		
		        			
	}

}

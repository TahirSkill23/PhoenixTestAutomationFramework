package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	@Test
	public void userdetailsAPItest() throws IOException {
		Header authtHeader=new Header("Authorization",AuthTokenProvider.getToken(Role.SUP));
		given().
		        baseUri(ConfigManager.getProperty("BASE_URI"))
		        .and()
		        .contentType(ContentType.JSON)
		        .header(authtHeader)
		        .and()
		        .accept(ContentType.JSON)
		        .log().headers()
		        .log().uri()
		        .log().method()
		        .log().body()
		        .when()
		        .get("userdetails")
		        .then()
		        .log().all()
		        .statusCode(200)
		        .time(Matchers.lessThan(1500L))
		        .and()
		        .body("message",Matchers.equalTo("Success"))
		        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/userDetailsResponseSchema.json"));
		     		
		        			
	}

}

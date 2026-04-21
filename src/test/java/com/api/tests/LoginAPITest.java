package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	//@ Tahir Mohd Asif
	@Test
	public void loginAPITest() throws IOException {
    UserCredentials userCreds=new UserCredentials("iamfd", "password");
		given()
              .baseUri(ConfigManager.getProperty("BASE_URI"))
              .and()
              .contentType(ContentType.JSON)
              .accept(ContentType.JSON)
              .and()
              .body(userCreds)
              .and()
              .log().uri()
              .log().headers()
              .log().body()
              .and()
        .when()
              .post("login")
        .then().log().all()
               .statusCode(200)
               .and()
               .body("message", Matchers.equalTo("Success"))
               .body("data.token", Matchers.notNullValue(null))
               .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"))
               .and()
               .time(Matchers.lessThan(3000L));
              
	}

}

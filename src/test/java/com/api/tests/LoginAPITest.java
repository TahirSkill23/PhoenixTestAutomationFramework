package com.api.tests;

import static io.restassured.RestAssured.DEFAULT_AUTH;
import static io.restassured.RestAssured.given;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest {
	@Test
	public void loginAPITest() {
		
    UserCredentials userCreds=new UserCredentials("iamfd", "password");
		given()
              .baseUri("http://64.227.160.186:9000/v1")
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
               .time(Matchers.lessThan(1800L));
	}

}

package com.api.utils;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider() {
		
	}

	public static String getToken(Role role) throws IOException {
		
		UserCredentials userCredentials = null;
		if(role==Role.FD) {
			userCredentials=new UserCredentials("iamfd", "password");
		}	
		else if(role==Role.SUP) {
			userCredentials=new UserCredentials("iamsup", "password");

		}
		else if(role==Role.ENG) {
			userCredentials=new UserCredentials("iameng", "password");

		}
		else if(role==Role.QC) {
			userCredentials=new UserCredentials("iamqc", "password");

		}
	//	else {throw new RuntimeException("Please use Valid Roles: FD/SUP/QC/ENG");}
      
		String token=given()
        .baseUri(ConfigManager.getProperty("BASE_URI"))
        .and()
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)
        .body(userCredentials)
        .when()
        .post("login")
        .then()
        .log().ifValidationFails()
        .statusCode(200).body("message", Matchers.equalTo("Success"))
        .extract()
        .response()
        .body()
        .jsonPath()
        .getString("data.token");
		System.out.println("Token: "+token);
		return token;
		
	}

}

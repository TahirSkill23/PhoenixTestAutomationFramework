package com.api.utils;

import org.apache.commons.codec.language.bm.RuleType;
import org.hamcrest.Matchers;

import static com.api.constant.Role.*;

import java.io.IOException;

import com.api.constant.Role;
import com.api.request.model.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {
	// This method work for get request and delete request
	public static RequestSpecification requestSpec() {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.BODY)
				.log(LogDetail.HEADERS).build();
		return requestSpecification;
	}
	// Here we are doing Method Overloading and we can pass the Body from here.
	// This method will be used for Post/PUT/PTACH request

	public static RequestSpecification requestSpec(Object payload) {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).setBody(payload).log(LogDetail.URI).log(LogDetail.METHOD)
				.log(LogDetail.BODY).log(LogDetail.HEADERS).build();
		return requestSpecification;
	}

	public static RequestSpecification requestSpecificationWithAuth(Role role) throws IOException {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
				.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.BODY).log(LogDetail.HEADERS).build();
		return requestSpecification;

	}
	
	public static RequestSpecification requestSpecificationWithAuth(Role role, Object payload) throws IOException {
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setBody(payload)
				.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.BODY).log(LogDetail.HEADERS).build();
		return requestSpecification;

	}

	public static ResponseSpecification responseSpe_OK() {
		ResponseSpecification reponseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(2000L)).expectStatusCode(200).log(LogDetail.ALL).build();
		return reponseSpecification;
	}

	public static ResponseSpecification responseSpe_JSON(int statusCode) {
		ResponseSpecification reponseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectResponseTime(Matchers.lessThan(2000L)).expectStatusCode(statusCode).log(LogDetail.ALL).build();
		return reponseSpecification;
	}

	public static ResponseSpecification responseSpe_Text(int statusCode) {
		ResponseSpecification reponseSpecification = new ResponseSpecBuilder()
				.expectResponseTime(Matchers.lessThan(2000L)).expectStatusCode(statusCode).log(LogDetail.ALL).build();
		return reponseSpecification;
	}

}

package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserBean;

import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPIJsonDataDrivenTest {
	
	@Test(description = "Verifying login API is working for each user present in Json file", groups = { "api", "regression",
			"smoke" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIJsonDataProvider")
	public void loginAPITest(UserCredentials userCredentials) {
		given().spec(SpecUtil.requestSpec(userCredentials)).and().when().post("login").then().spec(SpecUtil.responseSpe_OK())
				.and().body("message", Matchers.equalTo("Success")).body("data.token", Matchers.notNullValue(null))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}

}

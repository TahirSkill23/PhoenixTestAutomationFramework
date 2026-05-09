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

public class LoginAPIDataDrivenTest {
	
	@Test(description = "Verifying login API is working for each user", groups = { "api", "regression",
			"smoke" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "LoginAPIDataProvider")
	public void loginAPITest(UserBean userbean) {
		given().spec(SpecUtil.requestSpec(userbean)).and().when().post("login").then().spec(SpecUtil.responseSpe_OK())
				.and().body("message", Matchers.equalTo("Success")).body("data.token", Matchers.notNullValue(null))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));

	}

}

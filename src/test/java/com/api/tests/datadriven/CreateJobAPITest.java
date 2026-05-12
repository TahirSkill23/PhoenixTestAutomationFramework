package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {

	@Test(description = "Verify CreateJob API is able to create job for inwarranty flow", groups = { "api",
			"regression",
			"datadriven" }, dataProviderClass = com.dataproviders.DataProviderUtils.class, dataProvider = "CreateJobAPiDataProvider")
	public void createJobAPITest(CreateJobPayload craeteJobPayload) throws IOException {
		given().spec(SpecUtil.requestSpecificationWithAuth(Role.FD, craeteJobPayload)).when().post("job/create").then()
				.spec(SpecUtil.responseSpe_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.id", Matchers.notNullValue()).body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}

package com.api.tests;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.Customer_address;
import com.api.request.model.Customer_product;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() throws IOException {
		Customer customer=new Customer("Tahasf", "Mohd", "9897462699", "", "ttest123@putsbox.com", "");
		Customer_address customerAddress=new Customer_address("207", "Aerialux", "UCO Bank Ln", "Restro", "Gachibowli", "500032", "India", "TG");
		Customer_product customerProduct=new Customer_product("2025-07-31T18:30:00.000Z", "44453143462197", "44453143462197", "44453143462197", "2025-07-31T18:30:00.000Z", 1, 2);
		Problems problems=new Problems(1, "Heating Issue");
		Problems [] problemsArray=new Problems[1];
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		CreateJobPayload createJobPayload=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemList);
		
		given()
		.spec(SpecUtil.requestSpecificationWithAuth(Role.FD, createJobPayload))
		.when()
		.post("job/create")
		.then()
		.spec(SpecUtil.responseSpe_OK())
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", Matchers.equalTo("Job created successfully. "))
		.body("data.id", Matchers.notNullValue())
		.body("data.mst_service_location_id", Matchers.equalTo(1))
		.body("data.job_number", Matchers.startsWith("JOB_"));
  

}

}

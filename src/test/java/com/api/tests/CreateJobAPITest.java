package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Role;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.Customer_address;
import com.api.request.model.Customer_product;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	private CreateJobPayload createJobPayload;
	@BeforeMethod(description="craeting payload forCreateJob API")
	public void setUp() {
		Customer customer=new Customer("Tahasf", "Mohd", "9897462699", "", "ttest123@putsbox.com", "");
		Customer_address customerAddress=new Customer_address("207", "Aerialux", "UCO Bank Ln", "Restro", "Gachibowli", "500032", "India", "TG");
		Customer_product customerProduct=new Customer_product(DateTimeUtil.getTimeDaysAgo(10), "44473143462197", "44473143462197", "44473143462197", DateTimeUtil.getTimeDaysAgo(10), 
				Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		Problems problems=new Problems(Problem.OVERHEATING.getCode(), "Heating Issue");
		Problems [] problemsArray=new Problems[1];
		List<Problems> problemList=new ArrayList<Problems>();
		problemList.add(problems);
		 createJobPayload=new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerProduct, problemList);
	}
	
	@Test(description = "Verify CreateJob API is able to create job for inwarranty flow", groups = {"api","regression","smoke"})
	public void createJobAPITest() throws IOException {
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

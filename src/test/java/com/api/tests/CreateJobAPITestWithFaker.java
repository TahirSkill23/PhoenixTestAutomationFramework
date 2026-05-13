package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.Customer_address;
import com.api.request.model.Customer_product;
import com.api.request.model.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.SpecUtil;
import com.github.javafaker.Faker;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITestWithFaker {
	private final static String COUNTRY = "India";
	private CreateJobPayload createJobFakerPayload;

	@BeforeMethod(description = "craeting payload forCreateJob API")
	public void setUp() {
		Faker faker = new Faker(new Locale("en-IND"));
		String fName = faker.name().firstName();
		String lName = faker.name().lastName();
		String mobileNumber = faker.numerify("989#######");
		String alternateMobileNumber = faker.numerify("999#######");
		String customerEmailaddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();

		Customer customer = new Customer(fName, lName, mobileNumber, alternateMobileNumber, customerEmailaddress,
				altCustomerEmailAddress);
		System.out.println(customer);

		String flatNumber = faker.numerify("###");
		String aprtmentName = faker.address().firstName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().lastName();
		String area = faker.address().lastName();
		String pincode = faker.numerify("#####");

		String state = faker.address().state();

		Customer_address customerAddress = new Customer_address(flatNumber, aprtmentName, streetName, landmark, area,
				pincode, COUNTRY, state);
		System.out.println(customerAddress);

		String dop = DateTimeUtil.getTimeDaysAgo(20);
		String imeiSerialNumber = faker.numerify("###############");
		String popurl = faker.internet().url();

		Customer_product customerProduct = new Customer_product(dop, imeiSerialNumber, imeiSerialNumber,
				imeiSerialNumber, popurl, 1, 1);
		System.out.println(customerProduct);

		String fakereark = faker.lorem().sentence(4);
		Random random = new Random();
		int problemId = random.nextInt(10) + 1;

		Problems problem = new Problems(problemId, fakereark);
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problem);

		createJobFakerPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress,
				customerProduct, problemList);
		System.out.println(createJobFakerPayload);
	}

	@Test(description = "Verify CreateJob API is able to create job for inwarranty flow", groups = { "api",
			"regression", "smoke" })
	public void createJobAPITest() throws IOException {
		given().spec(SpecUtil.requestSpecificationWithAuth(Role.FD, createJobFakerPayload)).when().post("job/create")
				.then().spec(SpecUtil.responseSpe_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.id", Matchers.notNullValue()).body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}

}

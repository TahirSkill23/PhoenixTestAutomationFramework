package com.api.tests;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.Customer_address;
import com.api.pojo.Customer_product;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() throws IOException {
		Customer customer=new Customer("Tahasf", "Mohd", "9897462699", "", "ttest123@putsbox.com", "");
		Customer_address customerAddress=new Customer_address("207", "Aerialux", "UCO Bank Ln", "Restro", "Gachibowli", "500032", "India", "TG");
		Customer_product customerProduct=new Customer_product("2025-07-31T18:30:00.000Z", "19983143462100", "71983143462197", "71983143462197", "2025-07-31T18:30:00.000Z", 1, 2);
		Problems problems=new Problems(1, "Heating Issue");
		Problems [] problemsArray=new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload=new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
		.spec(SpecUtil.requestSpecificationWithAuth(Role.FD, createJobPayload))
		.when()
		.post("job/create")
		.then()
		.spec(SpecUtil.responseSpe_OK());
  

}

}

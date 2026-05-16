package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.Customer_address;
import com.api.request.model.Customer_product;
import com.api.request.model.Problems;
import com.github.javafaker.Faker;

public class FakerDataGenerator {
	private static Faker faker = new Faker(new Locale("en-IND"));
	private final static String COUNTRY = "India";
	private static Random random = new Random();
	private final static int MST_SERVICE_LOCATION_ID = 0;
	private final static int MST_PLATFORM_ID = 2;
	private final static int MST_WARRANTY_STATUS_ID = 1;
	private final static int MST_OEN_ID = 1;
	private final static int PRODUCT_ID = 1;
	private final static int MST_MODEL_ID = 1;
	private final static int validProblemsId[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	private FakerDataGenerator() {

	}

	public static CreateJobPayload generatefakeCreateJobData() {

		Customer customer = generateFakeCustomerData();
		Customer_address customerAddress = generatefakeCustomerAddressData();
		Customer_product customerProduct = generateFakeCustomerProduct();
		List<Problems> problemList = generateFakeProblemList();
		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRANTY_STATUS_ID, MST_OEN_ID, customer, customerAddress, customerProduct, problemList);
		return payload;
	}

	public static Iterator<CreateJobPayload> generatefakeCreateJobData(int count) {
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		for (int i = 1; i <= count; i++) {
			Customer customer = generateFakeCustomerData();
			Customer_address customerAddress = generatefakeCustomerAddressData();
			Customer_product customerProduct = generateFakeCustomerProduct();
			List<Problems> problemList = generateFakeProblemList();
			CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRANTY_STATUS_ID, MST_OEN_ID, customer, customerAddress, customerProduct, problemList);
			payloadList.add(payload);
		}
		return payloadList.iterator();
	}

	private static List<Problems> generateFakeProblemList() {
		int problemIdcount = random.nextInt(3) + 1;
		String fakeremark;
		int problemIdIndex;
		Problems problem;
		List<Problems> problemList = new ArrayList<Problems>();

		for (int i = 1; i <= problemIdcount; i++) {
			fakeremark = faker.lorem().sentence(5);
			problemIdIndex = random.nextInt(validProblemsId.length);
			problem = new Problems(validProblemsId[problemIdIndex], fakeremark);
			problemList.add(problem);
		}
		return problemList;
	}

	private static Customer_product generateFakeCustomerProduct() {
		String dop = DateTimeUtil.getTimeDaysAgo(20);
		String imeiSerialNumber = faker.numerify("###############");
		String popurl = faker.internet().url();

		Customer_product customerProduct = new Customer_product(dop, imeiSerialNumber, imeiSerialNumber,
				imeiSerialNumber, popurl, PRODUCT_ID, MST_MODEL_ID);
		return customerProduct;
	}

	private static Customer_address generatefakeCustomerAddressData() {
		String flatNumber = faker.numerify("###");
		String aprtmentName = faker.address().firstName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().lastName();
		String area = faker.address().lastName();
		String pincode = faker.numerify("#####");

		String state = faker.address().state();

		Customer_address customerAddress = new Customer_address(flatNumber, aprtmentName, streetName, landmark, area,
				pincode, COUNTRY, state);
		return customerAddress;
	}

	private static Customer generateFakeCustomerData() {
		String fName = faker.name().firstName();
		String lName = faker.name().lastName();
		String mobileNumber = faker.numerify("989#######");
		String alternateMobileNumber = faker.numerify("999#######");
		String customerEmailaddress = faker.internet().emailAddress();
		String altCustomerEmailAddress = faker.internet().emailAddress();

		Customer customer = new Customer(fName, lName, mobileNumber, alternateMobileNumber, customerEmailaddress,
				altCustomerEmailAddress);

		return customer;
	}

}

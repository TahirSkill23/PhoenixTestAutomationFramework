package com.api.pojo;

import java.util.Arrays;
import java.util.List;

public record CreateJobPayload(int mst_service_location_id, int mst_platform_id, int mst_warrenty_status_id,
		int mst_oem_id, Customer customer, Customer_address customer_address, Customer_product customer_product,
		List<Problems> problems) {

}

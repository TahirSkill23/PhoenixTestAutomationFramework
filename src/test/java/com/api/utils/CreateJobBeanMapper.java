package com.api.utils;

import java.util.ArrayList;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.Customer_address;
import com.api.request.model.Customer_product;
import com.api.request.model.Problems;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobBeanMapper {

// We will be giving the bean and it will create  the careteJob API Payload
	private CreateJobBeanMapper() {

	}

	public static CreateJobPayload mapper(CreateJobBean bean) {
		int mstServiceLocationID = Integer.parseInt(bean.getMst_service_location_id());
		int mstPlatformId = Integer.parseInt(bean.getMst_platform_id());
		int oemId = Integer.parseInt(bean.getMst_oem_id());
		int warrantyStatusId = Integer.parseInt(bean.getMst_warrenty_status_id());

		Customer customer = new Customer(bean.getCustomer__first_name(), bean.getCustomer__last_name(),
				bean.getCustomer__mobile_number(), bean.getCustomer__mobile_number_alt(), bean.getCustomer__email_id(),
				bean.getCustomer__email_id_alt());
		Customer_address customerAddress = new Customer_address(bean.getCustomer_address__flat_number(),
				bean.getCustomer_address__apartment_name(), bean.getCustomer_address__street_name(),
				bean.getCustomer_address__landmark(), bean.getCustomer_address__area(),
				bean.getCustomer_address__pincode(), bean.getCustomer_address__country(),
				bean.getCustomer_address__state());

		int productId = Integer.parseInt(bean.getCustomer_product__product_id());
		int modelId = Integer.parseInt(bean.getCustomer_product__mst_model_id());

		Customer_product customerProduct = new Customer_product(bean.getCustomer_product__dop(),
				bean.getCustomer_product__serial_number(), bean.getCustomer_product__imei1(),
				bean.getCustomer_product__imei2(), bean.getCustomer_product__popurl(), productId, modelId);

		List<Problems> problemList = new ArrayList<Problems>();
		int problemId = Integer.parseInt(bean.getProblems__id());

		Problems problems = new Problems(problemId, bean.getProblems__remark());
		problemList.add(problems);

		CreateJobPayload payload = new CreateJobPayload(mstServiceLocationID, mstPlatformId, warrantyStatusId, oemId,
				customer, customerAddress, customerProduct, problemList);
		
		 return payload;
	}
}

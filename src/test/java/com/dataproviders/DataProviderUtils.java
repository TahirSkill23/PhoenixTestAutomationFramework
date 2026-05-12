package com.dataproviders;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CSVReaderUtil;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {
	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		return CSVReaderUtil.loadCsv("testdata/LoginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobAPiDataProvider", parallel=true)
	public static Iterator<CreateJobPayload> createjobAPIDataProvider() {
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtil.loadCsv("testdata/CreateJobData.csv",
				CreateJobBean.class);
		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayload);
		}
		return payloadList.iterator();
	}
}

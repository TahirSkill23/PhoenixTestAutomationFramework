package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {

	public static <T> Iterator<T> loadJSON(String fileName, Class<T[]> apiClass) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		ObjectMapper objectMapper = new ObjectMapper();
//		 UserCredentials usercredentials=objectMapper.readValue(is, UserCredentials.class);
//		 System.out.println(usercredentials);
//		 
//		 System.out.println(usercredentials.username());
//		 System.out.println(usercredentials.password());

// This is correct for Single JSON Data but if we have multiple data in JSON Array then we need to handle it as below		 

		T[] classArray;
		List<T> list = null;
		try {
			classArray = objectMapper.readValue(is, apiClass);
			list = Arrays.asList(classArray);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return list.iterator();

	}

}

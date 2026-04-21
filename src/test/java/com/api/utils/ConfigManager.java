package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private ConfigManager() {
		// private constructor
	}

	private static String path;
	private static String env;
	private static Properties prop = new Properties(); // Object creation of Properties Class

	static {
		env = System.getProperty("env", "qa");
		env = env.toLowerCase().trim();
		System.out.println("Test is running in an ENV: " + env);

		switch (env) {
		case "qa" -> path = "config/config.qa.properties";

		case "dev" -> path = "config/config.dev.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.qa.properties";

		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		if (input == null) {
			throw new RuntimeException("Cannot find the file at the specified Path: " + path);
		}
		try {

			prop.load(input);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key)  {

		return prop.getProperty(key);
	}

}

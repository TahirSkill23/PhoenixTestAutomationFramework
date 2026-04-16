package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOLD {
	
	private ConfigManagerOLD() {
		
	}
	private static Properties prop = new Properties(); // Object creation of Properties Class

	static {
		File configFile = new File(
				System.getProperty("user.dir") + File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader configFileReader = null;
		try {
			configFileReader = new FileReader(configFile);
			prop.load(configFileReader);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) throws IOException {

		// Need to load the Properties file using load() method

		return prop.getProperty(key);
	}

}

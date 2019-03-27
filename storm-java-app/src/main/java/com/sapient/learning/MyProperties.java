package com.sapient.learning;

import java.util.Properties;
import java.util.ResourceBundle;

public class MyProperties {
	
	Properties properties = new Properties();
	ResourceBundle rb;
	
	MyProperties() {		
		String env = System.getProperties().getProperty("env");
		
		if (env != null) {
			rb = ResourceBundle.getBundle("application-"+env);
		} else {
			rb = ResourceBundle.getBundle("application");
		}
	}
	
	public String getValue(String key) {
		
		return rb.getString(key);
	}

}

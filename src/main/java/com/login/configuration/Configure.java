package com.login.configuration;

import java.util.ResourceBundle;

public class Configure {	
		public static String getConfig(String conf) {
			String value = "";
			
			ResourceBundle rb = ResourceBundle.getBundle("config.config");
			value = rb.getString(conf);
				
			return value;
		}
}

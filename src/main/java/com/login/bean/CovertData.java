package com.login.bean;

import java.util.Base64;

public class CovertData {
    public String EncodePassword(String password) {  
        System.out.println("Sample String:\n" + password);
        String encodePassword = Base64.getEncoder().encodeToString(password.getBytes());
        System.out.println("Encoded String:\n" + encodePassword);       
		return encodePassword;
    }
    
	public String DecodePassword(String password) {
	    System.out.println("Encoded String:\n" + password);
	    byte[] actualByte = Base64.getDecoder().decode(password);
	    String decodePassword = new String(actualByte);
	    System.out.println("actual String:\n" + decodePassword);
	    
		return decodePassword;
	}

}

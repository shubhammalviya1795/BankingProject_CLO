package com.intellect.igcb.autotest.utility;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
	
	Map<String, String> attributes;
	Map<String, Object> objectAttributes;
	
	public static String KEY_var_new_wrkitm_id = "var_new_wrkitm_id";
	public static String KEY_var_ntb_new_wrkitm_id = "var_ntb_new_wrkitm_id";
			
	
	private static ApplicationContext instance = null;
	
	private ApplicationContext() {
		attributes=new HashMap<String, String>();
		objectAttributes=new HashMap<String, Object>();
	}
	
	public static ApplicationContext getInstance() {
		if(instance ==null) {
			//to make thread safe
			synchronized (ApplicationContext.class) {
				//check again as multiple threads can reach above step
				if(instance ==null) {
					instance = new ApplicationContext();
				}				
			}
		}
		return instance;
	}
	
	public void storeAttribute(String aKey, String aValue) {
		attributes.put(aKey, aValue);
		System.out.println("Stored Key::" + aKey + " Value::" + aValue);
	}
	
	public void storeAttribute(String aKey, Object aValue) {
		objectAttributes.put(aKey, aValue);
		System.out.println("Stored Key::" + aKey + " Value::" + aValue);
	}
	
	public String getAttributeValue(String aKey) {
		System.out.println("Retrieving : value for " + aKey);
		return attributes.get(aKey);
	}
	
	public Object getObjectAttributeValue(String aKey) {
		System.out.println("Retrieving : value for " + aKey);
		return objectAttributes.get(aKey);
	}
	
	public void clearAll() {
		attributes.clear();
	}

}

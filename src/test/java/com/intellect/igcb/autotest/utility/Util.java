package com.intellect.igcb.autotest.utility;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

public class Util {

	/**
	 * Get the value from Application Context else return the key.
	 * 
	 * @param aWorkItemKey
	 * @return
	 */
	public static String getValueFromApplicationContext(String aWorkItemKey) {
		String tmpWorkItemId = ApplicationContext.getInstance().getAttributeValue(aWorkItemKey);
		if (tmpWorkItemId != null && !"".equals(tmpWorkItemId.trim())) {
			return tmpWorkItemId;
		}
		return aWorkItemKey;
	}

	public static String getLocatorFromApplicationContextObjRepository(String aKey) {
		Map<String, String> tmpObjRepo = (Map<String, String>) ApplicationContext.getInstance()
				.getObjectAttributeValue("OBJECT_REPOSITORY");
		if (tmpObjRepo != null) {
			return tmpObjRepo.get(aKey);
		}

		return null;
	}

	public static String generateAlphabeticString(int bound) {

		return RandomStringUtils.randomAlphabetic(bound).toUpperCase();

	}

	public static String generateAlphaNumericString(int bound) {

		return RandomStringUtils.randomAlphanumeric(bound).toUpperCase();

	}

	public static String generateNumericString(int bound) {

		return RandomStringUtils.randomNumeric(bound);

	}

}

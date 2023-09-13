package com.intellect.igcb.autotest.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("TRN_USER_REGISTRATION")
@IdName("registration_id")
public class UserRegistration extends Model {

	public Long getRegistrationId() {
		return getLong("registration_id");
	}

	public String getMobileNumber() {
		return getString("mobile_number");
	}

	public String getChannel() {
		return getString("channel");
	}

	public String getBusinessSegment() {
		return getString("business_segment");
	}

	public String getApplicationNumber() {
		return getString("application_number");
	}

}

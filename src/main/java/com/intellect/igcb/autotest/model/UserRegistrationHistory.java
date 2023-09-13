package com.intellect.igcb.autotest.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("TRN_USER_REGISTRATION_HIST")
@IdName("audit_id")
public class UserRegistrationHistory extends Model{
	
	public Long getAuditId() {
		return getLong("audit_id");
	}
	
	public Long getRegistrationId() {
		return getLong("registration_id");
	}

}

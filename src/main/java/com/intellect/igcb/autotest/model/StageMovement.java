package com.intellect.igcb.autotest.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("trn_stage_movement")
public class StageMovement extends Model{
	
	public Long getId() {
		return getLong("id");
	}
	
	public Long getRegistrationId() {
		return getLong("registration_id");
	}

}

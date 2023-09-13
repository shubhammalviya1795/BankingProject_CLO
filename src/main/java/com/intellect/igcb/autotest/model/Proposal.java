package com.intellect.igcb.autotest.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("clo_tenant.clo_trn_proposals")
public class Proposal extends Model{
	
	public Long getId() {
		return getLong("id");
	}
	
	public String getAppRefNumber() {
		return getString("app_ref_number");
	}

}

package com.intellect.igcb.autotest.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("clo_tenant.trn_facility_dtls")
public class Facility extends Model{
	
	public Long getId() {
		return getLong("id");
	}
	
	public Double getTrnProposalId() {
		return getDouble("trn_prposal_id");
	}

}

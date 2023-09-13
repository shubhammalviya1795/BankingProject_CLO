package com.intellect.igcb.autotest.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("CLO_TENANT.CLO_TRN_INDVDL_DTL")
public class IndividualDetails extends Model {

	public Long getId() {
		return getLong("id");
	}

	public String getAppRefNumber() {
		return getString("app_ref_number");
	}
}

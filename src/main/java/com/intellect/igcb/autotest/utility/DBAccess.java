package com.intellect.igcb.autotest.utility;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.javalite.activejdbc.Base;

import com.intellect.igcb.autotest.model.AddressDetails;
import com.intellect.igcb.autotest.model.CifDetails;
import com.intellect.igcb.autotest.model.CorporateDetails;
import com.intellect.igcb.autotest.model.DemographicDetails;
import com.intellect.igcb.autotest.model.Facility;
import com.intellect.igcb.autotest.model.IndividualDetails;
import com.intellect.igcb.autotest.model.Proposal;
import com.intellect.igcb.autotest.model.StageMovement;
import com.intellect.igcb.autotest.model.UserRegistration;
import com.intellect.igcb.autotest.model.UserRegistrationHistory;

public class DBAccess {
	
	private static SQLPropertiesReader propReader = SQLPropertiesReader.getInstance();

	public static void main(String args[]) {
		
		//System.out.println(getProposal("22012300000006").getId());
		//deleteProposal("22012300000006");
		//executeSqlFile("applicant_details_setup.sql");
		deleteUser("9876543222");

	}

	public static void open() {
		Base.open();
		System.out.println("Connection Open");
	}
	
	
	public static void executeSqlFile(String aFileName) {
		try {
			Base.open();
			List<String> sqlStmt = Files.readAllLines(Paths.get(ClassLoader.getSystemResource(aFileName).toURI()));
			for(String sql: sqlStmt) {
				if(sql == null || sql.trim().equals("")) {
					continue;
				}
				int tmpResult = Base.exec(sql);
				System.out.println("Query Execution Result = " + tmpResult);
			}			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Base.close();
		}		
	}
	
	public static void deleteUser(String aMobileNumber) {
		try {
			Base.open();
			UserRegistration ur = UserRegistration.findFirst("mobile_number = ?", aMobileNumber);
			if (ur == null) {
				System.out.println("No records found for mobile no " + aMobileNumber);
				return;
			}
			int result = StageMovement.delete("registration_id = ? ", ur.getId());
			System.out.println("Records delete for StageMovement = " + result);
			result = UserRegistrationHistory.delete("registration_id = ? ", ur.getId());
			System.out.println("Records delete for UserRegistrationHistory = " + result);
			result = CifDetails.delete("registration_id = ? ", ur.getId());
			System.out.println("Records delete for CifDetails = " + result);
			
			Proposal p = Proposal.findFirst("app_ref_number = ?", ur.getApplicationNumber());
			if (p == null) {
				System.out.println("No records found for app_ref_number " + ur.getApplicationNumber());
				System.out.println("Deleting Record with registration id = " + ur.getRegistrationId());
				boolean recordDeleted = ur.delete();
				System.out.println("Record deletion for Mobile Number " + aMobileNumber + " = " + recordDeleted);
				return;
			} 
			//delete address
			result = AddressDetails.delete("app_ref_number = ?", p.getAppRefNumber());
			System.out.println("Records delete for AddressDetails = " + result);
			//delete demographic
			result = DemographicDetails.delete("app_ref_number = ?", p.getAppRefNumber());
			System.out.println("Records delete for DemographicDetails = " + result);
			
			//delete indv
			result = IndividualDetails.delete("app_ref_number = ?", p.getAppRefNumber());
			System.out.println("Records delete for IndividualDetails = " + result);
			
			//delete corporate
			result = CorporateDetails.delete("app_ref_number = ?", p.getAppRefNumber());
			System.out.println("Records delete for CorporateDetails = " + result);			
			
			//delete facility
			result = Facility.delete("trn_proposal_id = ?", p.getId());
			System.out.println("Records delete for Facility = " + result);
			//delete proposal
			boolean rb = p.delete();
			System.out.println("Records delete for Proposal = " + rb);
			//delete trn user registration
			System.out.println("Deleting Record with registration id = " + ur.getRegistrationId());
			boolean recordDeleted = ur.delete();
			System.out.println("Record deletion for Mobile Number " + aMobileNumber + " = " + recordDeleted);
		} finally {
			Base.close();
			System.out.println("Connection closed");
		}
		
	}
	

	public static void deleteUserRegistrationByMobile(String aMobile) {
		try {
			Base.open();
			UserRegistration ur = UserRegistration.findFirst("mobile_number = ?", aMobile);
			if (ur == null) {
				System.out.println("No records found for mobile no " + aMobile);
				return;
			}
			
			List<StageMovement> smLst = StageMovement.find("registration_id = ? ", ur.getId());
			
			if(smLst != null) {
				for(StageMovement sm : smLst) {
					boolean r = sm.delete();
					System.out.println("Stahe movement delete = " + r);
				}
			}
			
			System.out.println("Deleting Record with registration id = " + ur.getRegistrationId());
			boolean recordDeleted = ur.delete();
			System.out.println("Record deletion for Mobile Number " + aMobile + " = " + recordDeleted);
		} finally {
			Base.close();
			System.out.println("Connection closed");
		}

	}
	
	public static UserRegistration findUserRegistrationByMobile(String aMobile) {
		try {
			Base.open();
			UserRegistration ur = UserRegistration.findFirst("mobile_number = ?", aMobile);
			if (ur == null) {
				System.out.println("No records found for mobile no " + aMobile);
				return null;
			}
			return ur;
		} finally {
			Base.close();
			System.out.println("Connection closed");
		}

	}
	
	
	public static void createNewNTBUserRegistration() {
		try {
			Base.open();
			int result = Base.exec(propReader.getProperty("DBNEG6783D_USER_REGITRATION"));
			System.out.println("Result = " + result);
			if(result == 0) {
				return;
			}
			String queries[] = propReader.getProperty("DBNEG6783D_STAGE_MOVEMENT").split("#");
			for(String q: queries) {
				int r = Base.exec(q);
			}
			
		}finally {
			Base.close();
			System.out.println("Connection closed");
		}
		
	}
	
	public static void createNewNTBUserRegistrationWithProposal() {
		try {
			Base.open();
			int result = Base.exec(propReader.getProperty("DBNEG6783D_USER_REGITRATION_2"));
			System.out.println("Result User Registration= " + result);
			String queries[] = propReader.getProperty("DBNEG6783D_STAGE_MOVEMENT_2").split("#");
			for(String q: queries) {
				int r = Base.exec(q);
			}
			result = Base.exec(propReader.getProperty("DBNEG6783D_PROPOSAL"));
			System.out.println("Result Proposal = " + result);
			result = Base.exec(propReader.getProperty("DBNEG6783D_PRODUCT"));
			System.out.println("Result Product = " + result);
		}finally {
			Base.close();
			System.out.println("Connection closed");
		}
		
	}
	
	public static Proposal getProposal(String aAppRefNumber) {
		try {
			Base.open();
			Proposal ur = Proposal.findFirst("app_ref_number = ?", aAppRefNumber);
			if (ur == null) {
				System.out.println("No records found for mobile no " + aAppRefNumber);
				return null;
			}
			return ur;
		} finally {
			Base.close();
			System.out.println("Connection closed");
		}
	}
	
	public static void deleteProposal(String aAppRefNumber) {
		try {
			Base.open();
			Proposal ur = Proposal.findFirst("app_ref_number = ?", aAppRefNumber);
			if (ur == null) {
				System.out.println("No records found for app_ref_number " + aAppRefNumber);
				return;
			} 
			
			List<Facility> fl =Facility.find("trn_proposal_id = ?", ur.getId());
			if(fl != null) {
				for(Facility f : fl) {
					System.out.println("Facility Id = " + f.getId());
					boolean deleteStatus = f.delete();
					System.out.println("Facility deleted = " + deleteStatus);
				}
			}			
			System.out.println("Deleting Proposal ecord with app_ref_number = " + ur.getAppRefNumber());
			boolean recordDeleted = ur.delete();
			System.out.println("Proposal deletion for app_ref_number " + aAppRefNumber + " = " + recordDeleted);
		} finally {
			Base.close();
			System.out.println("Connection closed");
		}
	}

	public static void close() {
		Base.close();
		System.out.println("Connection closed");
	}

}

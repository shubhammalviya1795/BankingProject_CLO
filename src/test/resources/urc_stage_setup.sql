Insert into TRN_USER_REGISTRATION (registration_id,proposal_id,application_number,customer_id,mobile_number,name,tax_id,national_id,resume_point,channel,business_segment,unverified_lead_id,verified_lead_id,constitution,pincode,employee_code,email_id,crm_branch,sme_branch,percentage_completion,status,created_by,created_on,updated_by,updated_on,udf1,udf2,udf3,udf4,udf5,udf6,udf7,udf8,udf9,udf10) values (792,null,'22021700000086','10455','9876543222','M/S. Virat A Services','GBNAG6783G',null,'OfferScreen','BANK','BBG',null,null,'A','110026',null,null,'7579','1349',null,null,'9876543222',to_timestamp('2022-02-17 19:37:51.232','null'),null,to_timestamp('2022-02-17 19:38:13.248','null'),null,null,null,null,null,null,null,null,null,null)
Insert into TRN_STAGE_MOVEMENT (id,registration_id,resume_point,status,created_on) values (5411,792,null,'UnVerified',to_timestamp('2022-02-17 19:37:51.232','null'))
Insert into TRN_STAGE_MOVEMENT (id,registration_id,resume_point,status,created_on) values (5412,792,null,'Verified',to_timestamp('2022-02-17 19:37:51.232','null'))
Insert into TRN_STAGE_MOVEMENT (id,registration_id,resume_point,status,created_on) values (5413,792,'DedupeScreen',null,to_timestamp('2022-02-17 19:37:51.232','null'))
Insert into TRN_CIF_DETAILS (id,registration_id,application_number,customer_id,customer_details,created_on,created_by,updated_on,updated_by,udf1,udf2,udf3,udf4,udf5,udf6,udf7,udf8,udf9,udf10) values (361,792,'22021700000086','10455','{"corporateList":[],"individualList":[{"AddressDetail":[{"AddrLine2":"Address Line 2","CityCode":"614","AddrLine1":"Address Line 1","AddrLine3":"Address Line 3","AddrType":"1","CustomerSubType":"001","Udf1":"100","Udf2":"New Garden Premises","Udf3":"A2","AddressTypedesc":"Operating Office","Udf4":"Street number: 45","Udf5":"TIRUVALLUR Suburb","Udf6":"Mahatma Gandhi Street","Landmark":"testtwo","MailingAddr":"false","StateCode":"71","Udf7":"TIRUVALLUR locality","Udf8":"TIRUVALLUR","Udf9":"10/12/2035","City":"TIRUVALLUR","CustomerType":"001","State":"Tamil Nadu","TrnCustomerId":"1144","Country":"India","Id":"985","OwnershipStatus":"001","Pincode":"600123"},{"AddrLine2":"Lake Street","CityCode":"613","AddrLine1":"Flat No. 105","AddrLine3":"Indira Colony","AddrType":"2","CustomerSubType":"001","Udf1":"100","Udf2":"New Garden Premises","Udf3":"A2","AddressTypedesc":"Operating Office","Udf4":"Street number: 45","Udf5":"TIRUVALLUR Suburb","Udf6":"Mahatma Gandhi Street","Landmark":"Opposite HDFC Bank","MailingAddr":"true","StateCode":"71","Udf7":"TIRUVALLUR locality","Udf8":"TIRUVALLUR","Udf9":"10/12/2035","City":"CHENNAI","CustomerType":"001","State":"Tamil Nadu","TrnCustomerId":"1144","Country":"India","Id":"985","OwnershipStatus":"001","Pincode":"600123"}],"DemographicDetails":{"LandlineNumber ":"02258956556","Gender":"0","TaxId":"GBNAG6783G","CustomerSubType":"001","V_D_CUST_BRANCH_CODE":"","Passport":"1200023565","FaxNumber":"+44 161 999 8888","customerId":"10455","Email1":"myoriginalmail@gmail.com","CustomerName":"Virat Services","DateOfBirth":"1970-05-02 00:00:00.0","Designation":"002","FirstName":"Virat","NationalId":"5456987455","MobileNumber1":"9876543225","MobileNumber2":"8965741230","MiddleName":"","CustomerType":"001","AadharSeeding":"12345678","LocalName":"Local Name","EmailForComm":"1","AreaCode":"400085","LastName":"Services","Id":"1504","Caste":"Open"}}]}',to_timestamp('2022-02-17 19:38:32.295','null'),'9876543222',null,null,null,null,null,null,null,null,null,null,null,null)
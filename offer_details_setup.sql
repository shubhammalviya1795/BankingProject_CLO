Insert into TRN_USER_REGISTRATION (registration_id,proposal_id,application_number,customer_id,mobile_number,name,tax_id,national_id,resume_point,channel,business_segment,unverified_lead_id,verified_lead_id,constitution,pincode,employee_code,email_id,crm_branch,sme_branch,percentage_completion,status,created_by,created_on,updated_by,updated_on,udf1,udf2,udf3,udf4,udf5,udf6,udf7,udf8,udf9,udf10) values (306,null,'22020600000002','10461','9930390120','Mr. Virat K and Sons','AUUPS6110M',null,'OfferScreen','BANK','BBG',null,null,'H','110026',null,null,'7579','1349',null,null,'9930390120',to_timestamp('2022-02-06 10:31:50.4','null'),null,to_timestamp('2022-02-06 10:32:25.081','null'),null,null,null,null,null,null,null,null,null,null);

Insert into TRN_STAGE_MOVEMENT (id,registration_id,resume_point,status,created_on) values (1854,306,null,'UnVerified',to_timestamp('2022-02-06 10:31:50.4','null'));
Insert into TRN_STAGE_MOVEMENT (id,registration_id,resume_point,status,created_on) values (1855,306,null,'Verified',to_timestamp('2022-02-06 10:31:50.4','null'));
Insert into TRN_STAGE_MOVEMENT (id,registration_id,resume_point,status,created_on) values (1856,306,null,null,to_timestamp('2022-02-06 10:31:50.4','null'));
Insert into TRN_STAGE_MOVEMENT (id,registration_id,resume_point,status,created_on) values (1857,306,'DedupeScreen',null,to_timestamp('2022-02-06 10:31:50.4','null'));

Insert into TRN_CIF_DETAILS (id,registration_id,application_number,customer_id,customer_details,created_on,created_by,updated_on,updated_by,udf1,udf2,udf3,udf4,udf5,udf6,udf7,udf8,udf9,udf10) values (125,306,'22020600000002','10461','{"individualList":[],"corporateList":[{"AddressDetail":[{"AddrLine2":"Lake Street","CityCode":"BBNR","AddrLine1":"Beside Reliance Smart","AddrLine3":"","AddrType":"1","CustomerSubType":"002","Udf1":"100","Udf2":"New Garden Premises","Udf3":"A2","AddressTypedesc":"Showroom","Udf4":"Street number: 45","Udf5":"TIRUVALLUR Suburb","Udf6":"Mahatma Gandhi Street","Landmark":"Opposite TATA Motors","MailingAddr":"true","StateCode":"OR","Udf7":"TIRUVALLUR locality","Udf8":"TIRUVALLUR","Udf9":"10/12/2035","City":"BBNR","CustomerType":"001","State":"OR","TrnCustomerId":"1122","Country":"IN","Pincode":"751022"}],"DemographicDetails":{"LandlineNumber":"02254546532","CorpTaxId":"AUUPS6110M","Website":"website.com","MobileNumber1":"9876543233","MobileNumber2":"8523254565","CustomerType":"001","CustomerSubType":"002","CompanyName":"Virat and Sons","EmailForComm":"1","TrnCustomerId":"1122","ProposalId":"2181","customerId":"10461","RegistrationType":"","Email1":"marketingemail@gmail.com","AreaCode":"400025","RegistrationNumber":"Hqdsa123","Id":"1688","DateOfIncorporation":"2000-05-08 00:00:00.0","Constitution":"Trust"}}]}',to_timestamp('2022-02-06 10:32:31.971','null'),'9930390120',null,null,null,null,null,null,null,null,null,null,null,null);

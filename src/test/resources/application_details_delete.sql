DELETE FROM TRN_USER_REGISTRATION WHERE registration_id=112
DELETE FROM TRN_USER_REGISTRATION_HIST WHERE registration_id=112
DELETE FROM TRN_STAGE_MOVEMENT WHERE registration_id=112
DELETE FROM TRN_CIF_DETAILS WHERE registration_id=112
DELETE FROM CLO_TENANT.CLO_TRN_PROPOSALS WHERE id=486
DELETE FROM CLO_TENANT.TRN_FACILITY_DTLS WHERE trn_proposal_id=486
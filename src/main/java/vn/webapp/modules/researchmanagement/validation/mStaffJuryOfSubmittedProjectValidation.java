package vn.webapp.modules.researchmanagement.validation;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import vn.webapp.validation.UploadFileMaxSize;

public class mStaffJuryOfSubmittedProjectValidation {
	/** Set rules for fields */
	private int STFJUPRJ_ID;
	
	@NotEmpty
	private String STFJUPRJ_STAFFJURCODE;

	@NotEmpty
	private String STFJUPRJ_PRJCODE;

	public int getSTFJUPRJ_ID() {
		return STFJUPRJ_ID;
	}

	public void setSTFJUPRJ_ID(int sTFJUPRJ_ID) {
		STFJUPRJ_ID = sTFJUPRJ_ID;
	}

	public String getSTFJUPRJ_STAFFJURCODE() {
		return STFJUPRJ_STAFFJURCODE;
	}

	public void setSTFJUPRJ_STAFFJURCODE(String sTFJUPRJ_STAFFJURCODE) {
		STFJUPRJ_STAFFJURCODE = sTFJUPRJ_STAFFJURCODE;
	}

	public String getSTFJUPRJ_PRJCODE() {
		return STFJUPRJ_PRJCODE;
	}

	public void setSTFJUPRJ_PRJCODE(String sTFJUPRJ_PRJCODE) {
		STFJUPRJ_PRJCODE = sTFJUPRJ_PRJCODE;
	}
	
}

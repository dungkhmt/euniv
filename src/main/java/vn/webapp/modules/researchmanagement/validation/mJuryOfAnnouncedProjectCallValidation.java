package vn.webapp.modules.researchmanagement.validation;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import vn.webapp.validation.UploadFileMaxSize;

public class mJuryOfAnnouncedProjectCallValidation {
	/** Set rules for fields */
	private int JUSUPRJ_ID;
	
	@NotEmpty
	private String JUSUPRJ_STAFFCODE;

	private String JUSUPRJ_JURYRESEARCHPROJECTCODE;
	
	public String getJUSUPRJ_JURYRESEARCHPROJECTCODE() {
		return JUSUPRJ_JURYRESEARCHPROJECTCODE;
	}

	public void setJUSUPRJ_JURYRESEARCHPROJECTCODE(
			String jUSUPRJ_JURYRESEARCHPROJECTCODE) {
		JUSUPRJ_JURYRESEARCHPROJECTCODE = jUSUPRJ_JURYRESEARCHPROJECTCODE;
	}

	@NotEmpty
	private String JUSUPRJ_PRJCALLCODE;

	@NotEmpty
	private String JUPSURJ_ROLECODE;

	public int getJUSUPRJ_ID() {
		return JUSUPRJ_ID;
	}

	public void setJUSUPRJ_ID(int jUSUPRJ_ID) {
		JUSUPRJ_ID = jUSUPRJ_ID;
	}

	public String getJUSUPRJ_STAFFCODE() {
		return JUSUPRJ_STAFFCODE;
	}

	public void setJUSUPRJ_STAFFCODE(String jUSUPRJ_STAFFCODE) {
		JUSUPRJ_STAFFCODE = jUSUPRJ_STAFFCODE;
	}

	public String getJUSUPRJ_PRJCALLCODE() {
		return JUSUPRJ_PRJCALLCODE;
	}

	public void setJUSUPRJ_PRJCALLCODE(String jUSUPRJ_PRJCALLCODE) {
		JUSUPRJ_PRJCALLCODE = jUSUPRJ_PRJCALLCODE;
	}

	public String getJUPSURJ_ROLECODE() {
		return JUPSURJ_ROLECODE;
	}

	public void setJUPSURJ_ROLECODE(String jUPSURJ_ROLECODE) {
		JUPSURJ_ROLECODE = jUPSURJ_ROLECODE;
	}
	
	
}

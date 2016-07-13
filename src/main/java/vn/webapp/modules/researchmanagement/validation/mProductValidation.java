package vn.webapp.modules.researchmanagement.validation;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import vn.webapp.validation.UploadFileMaxSize;

public class mProductValidation {
	/** Set rules for fields */
	private int productId;

	private String productCode;
	
	@NotEmpty
	private String productCatCode;

	@NotEmpty
	private String productName;

	private int productBudget;

	@NotEmpty
	@Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])/[0-9]{4}$")
	private String productStartDate;

	@NotEmpty
	@Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])/[0-9]{4}$")
	private String productEndDate;

	@UploadFileMaxSize(20971520)
	private MultipartFile productSourceFile;

	private String productStatus;

	public int getproductId() {
		return productId;
	}

	public void setproductId(int productId) {
		this.productId = productId;
	}

	public String getproductCatCode() {
		return productCatCode;
	}

	public void setproductCatCode(String productCatCode) {
		this.productCatCode = productCatCode;
	}

	public String getproductName() {
		return productName;
	}

	public void setproductName(String productName) {
		this.productName = productName;
	}

	public int getproductBudget() {
		return productBudget;
	}

	public void setproductBudget(int productBudget) {
		this.productBudget = productBudget;
	}

	public String getproductStartDate() {
		return productStartDate;
	}

	public void setproductStartDate(String productStartDate) {
		this.productStartDate = productStartDate;
	}

	public String getproductEndDate() {
		return productEndDate;
	}

	public void setproductEndDate(String productEndDate) {
		this.productEndDate = productEndDate;
	}

	public MultipartFile getproductSourceFile() {
		return productSourceFile;
	}

	public void setproductSourceFile(MultipartFile productSourceFile) {
		this.productSourceFile = productSourceFile;
	}

	public String getproductStatus() {
		return productStatus;
	}

	public void setproductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getproductCode() {
		return productCode;
	}

	public void setproductCode(String productCode) {
		this.productCode = productCode;
	}
}

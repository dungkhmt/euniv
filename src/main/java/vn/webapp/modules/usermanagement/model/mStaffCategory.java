package vn.webapp.modules.usermanagement.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblstaffcategory")
public class mStaffCategory implements Serializable{
	@Id
    @GeneratedValue
    private int Staff_Category_ID;
    private String Staff_Category_Code;
    private String Staff_Category_Name;
    private String Staff_Category_AsciiName;
    
    @OneToMany(mappedBy="staffCategory")
    private Set<mStaff> staffs;

	public int getStaff_Category_ID() {
		return Staff_Category_ID;
	}

	public void setStaff_Category_ID(int staff_Category_ID) {
		Staff_Category_ID = staff_Category_ID;
	}

	public String getStaff_Category_Code() {
		return Staff_Category_Code;
	}

	public void setStaff_Category_Code(String staff_Category_Code) {
		Staff_Category_Code = staff_Category_Code;
	}

	public String getStaff_Category_Name() {
		return Staff_Category_Name;
	}

	public void setStaff_Category_Name(String staff_Category_Name) {
		Staff_Category_Name = staff_Category_Name;
	}

	public String getStaff_Category_AsciiName() {
		return Staff_Category_AsciiName;
	}

	public void setStaff_Category_AsciiName(String staff_Category_AsciiName) {
		Staff_Category_AsciiName = staff_Category_AsciiName;
	}

	public Set<mStaff> getStaffs() {
		return staffs;
	}

	public void setStaffs(Set<mStaff> staffs) {
		this.staffs = staffs;
	}
}

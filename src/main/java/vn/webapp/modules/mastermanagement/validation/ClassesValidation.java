/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.webapp.modules.mastermanagement.validation;

/**
* Set user authentication
* @author Tonytran
*/
import org.hibernate.validator.constraints.NotEmpty;

/*
 * Using a customization validator
 */

public class ClassesValidation {

    /** Set rules for fields*/
    @NotEmpty
    private String className;

    @NotEmpty
    private String classCode;
    
  
    private int classId;
    
    private String classFacultyCode;
    
    public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassFacultyCode() {
		return classFacultyCode;
	}

	public void setClassFacultyCode(String classFacultyCode) {
		this.classFacultyCode = classFacultyCode;
	}

	public String getClassYear() {
		return classYear;
	}

	public void setClassYear(String classYear) {
		this.classYear = classYear;
	}

	private String classYear;
    
}
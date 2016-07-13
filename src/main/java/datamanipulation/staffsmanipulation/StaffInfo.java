package datamanipulation.staffsmanipulation;

public class StaffInfo {

	public int index;
	public String FullName;
	public String LastName;
	public String department;
	public String faculty;
	public String email;
	public String sex;
	public String dateofbirth;
	
	public String departmentCode;
	public String facultyCode;
	
	
	public String toString(){
		return index + "," + FullName + "," + LastName + "," + department + "," + faculty + "," + email + "," + sex + "," + dateofbirth;
	}
	public StaffInfo(int index, String fullname, String lastname,
			String department, String faculty, String email, String sex,
			String dateofbirth) {
		this.index = index;
		this.FullName = fullname;
		this.LastName = lastname;
		this.department = department;
		this.faculty = faculty;
		this.email = email;
		this.sex = sex;
		this.dateofbirth = dateofbirth;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

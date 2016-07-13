package datamanipulation.staffsmanipulation;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class StaffUploader {
	public ArrayList<StaffInfo> staffs;
	public String DRIVER = "com.mysql.jdbc.Driver";
	public String PATH = "jdbc:mysql://localhost/hust_euniv";
	public PrintWriter log = null;
	
	public ArrayList<Department> getDeparmtentList(){
		ArrayList<Department> list = new ArrayList<Department>();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tbldepartment";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int ID = rs.getInt("DEPARTMENT_ID");
				String code = rs.getString("DEPARTMENT_Code");
				String name = rs.getString("DEPARTMENT_Name");
				String asciiname = rs.getString("DEPARTMENT_AsciiName");
				Department dept = new Department(ID,code,name,asciiname);
				list.add(dept);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return list;
	}
	public void initLog(){
		try{
			log = new PrintWriter("staff-manipulation-log.txt");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void finalize(){
		log.close();
	}
	public ArrayList<Faculty> getFacultyList(){
		ArrayList<Faculty> list = new ArrayList<Faculty>();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tblfaculty";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int ID = rs.getInt("FACULTY_ID");
				String code = rs.getString("FACULTY_CODE");
				String name = rs.getString("FACULTY_NAME");
				String asciiname = rs.getString("FACULTY_ASCIINAME");
				
				Faculty f = new Faculty(ID,code, name,asciiname);
				list.add(f);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	public boolean validEmail(String email){
		email = email.trim();
		if(email == null || email.equals("")) return false;
		if(email.indexOf("@") < 0) return false;
		if(email.indexOf("@hust.edu.vn") < 0) return false;
		return true;
	}
	public String normalizeEmail(String email){
		String delimiter = ",";
		if(email.indexOf(",") >=0) delimiter = ",";
		else if(email.indexOf("/") >= 0) delimiter = "/";
		else if(email.indexOf(" ") >= 0) delimiter = " ";
		
		email = email.substring(0,email.length()-1);
		String[] s = email.split(delimiter);
		for(int i = 0; i< s.length; i++)
			if(s[i].indexOf("@hust.edu.vn") >= 0) return s[i];
		return "anonymous@hust.edu.vn";
	}
	public ArrayList<StaffInfo> loadStaffFromExcel(String fn){
		ArrayList<StaffInfo> staffs = new ArrayList<StaffInfo>();
		try{
			FileInputStream f = new FileInputStream(new File(fn));
			HSSFWorkbook wb = new HSSFWorkbook(f);
			HSSFSheet sheet = wb.getSheetAt(1);
			Iterator<Row> iterator = sheet.iterator();
			Row r = iterator.next();// ignore first row
			while(iterator.hasNext()){
				r = iterator.next();
				Cell c0 = r.getCell(0);
				String s_index = c0.getStringCellValue();
				int index = Integer.valueOf(s_index);
				Cell c1 = r.getCell(1);
				String fullname = c1.getStringCellValue();
				
				Cell c2 = r.getCell(2);
				String lastName = c2.getStringCellValue();
				Cell c3 = r.getCell(3);
				String departmentName = c3.getStringCellValue();
				Cell c4 = r.getCell(4);
				String facultyName = c4.getStringCellValue();
				Cell c5 = r.getCell(5);
				String email = c5.getStringCellValue();
				Cell c6 = r.getCell(6);
				String sex = c6.getStringCellValue();
				Cell c7 = r.getCell(7);
				String dateOfBirth = c7.getStringCellValue();
				
				StaffInfo st = new StaffInfo(index, fullname, lastName, departmentName,facultyName,email,sex,dateOfBirth);
				if(validEmail(email)){
					st.email = normalizeEmail(email);
					if(st.email.equals("anonymous@hust.edu.vn"))
						log.println("Staff not valid: " + st.toString());
					else
						staffs.add(st);
				//System.out.println(fullname + "\t" + email + "\t" + dateOfBirth);
				}else{
					log.println("Staff not valid: " + st.toString());
				}
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return staffs;
	}
	public void clearUsers(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "delete from tblusers";
			Statement st = cn.createStatement();
			st.execute(sql);
			
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void clearStaffs(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "delete from tblstaffs";
			Statement st = cn.createStatement();
			st.execute(sql);
			
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void clearUserRoles(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "delete from tbluserroles";
			Statement st = cn.createStatement();
			st.execute(sql);
			
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void insertUsers(ArrayList<UserInfo> users){
		clearUsers();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			
			for(int i = 0; i < users.size(); i++){
				UserInfo ui = users.get(i);
				String sql = "insert into tblusers(Username,Password,Salt,Email,Enabled,User_Code) values(?,?,?,?,?,?)";
				System.out.println("insert users " + ui.Username + "," + ui.email + " --> start, finish " + i + "/" + users.size());
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1, ui.Username);
				st.setString(2, ui.Password);
				st.setString(3, ui.Salt);
				st.setString(4, ui.email);
				st.setInt(5, 1);
				st.setString(6, ui.UserCode);
				st.execute();
				
				System.out.println("insert users " + ui.email + " --> OK, finish " + i + "/" + users.size());
			}
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void insertUserRoles(ArrayList<UserRole> userroles){
		clearUserRoles();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			
			for(int i = 0; i < userroles.size(); i++){
				UserRole ui = userroles.get(i);
				String sql = "insert into tbluserroles(Username,Role) values(?,?)";
				System.out.println("insert userroles " + ui.userName + "," + ui.role + " --> start, finish " + i + "/" + userroles.size());
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1, ui.userName);
				st.setString(2, ui.role);
				st.execute();
				
				System.out.println("insert users " + ui.role + " --> OK, finish " + i + "/" + userroles.size());
			}
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void insertStaffs(ArrayList<StaffInfo> staffs){
		clearStaffs();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			
			for(int i = 0; i < staffs.size(); i++){
				StaffInfo st = staffs.get(i);
				String sql = "insert into tblstaffs(Staff_Code,Staff_Name,Staff_AsciiName,Staff_Email,Staff_Department_Code,"
						+ "Staff_Phone,Staff_Category_Code, Staff_User_Code,Staff_Faculty_Code,Staff_DateOfBirth,Staff_Gender) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?)";
				System.out.println("insert staff " + st.email + " --> start, finish " + i + "/" + staffs.size());
				PreparedStatement stm = cn.prepareStatement(sql);
				stm.setString(1,st.email);
				stm.setString(2,st.email);
				stm.setString(3,st.email);
				stm.setString(4,st.email);
				stm.setString(5,st.departmentCode);
				stm.setString(6,"");
				stm.setString(7,"LEC");
				stm.setString(8,st.email);
				stm.setString(9,st.facultyCode);
				stm.setString(10,st.dateofbirth);
				stm.setString(11,"");
				
				stm.execute();
				
				System.out.println("insert staff " + st.email + "," + st.departmentCode + "," + st.facultyCode + " --> OK, finish " + i + "/" + staffs.size());
			}
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void upload(String excelFilename){
		ArrayList<Department> departments = getDeparmtentList();
		ArrayList<Faculty> faculties = getFacultyList();
		ArrayList<StaffInfo> staffs = loadStaffFromExcel(excelFilename);
		
		System.out.println("dept = " + departments.size() + ", faculties.sz = " + faculties.size() + ", staffs.sz = " + staffs.size());
		
		HashMap<String, Department> mName2Department = new HashMap<String, Department>();
		HashMap<String, Faculty> mName2Faculty = new HashMap<String, Faculty>();
		for(Department dept: departments){
			mName2Department.put(dept.Name, dept);
			System.out.println("mName2Department.put(" + dept.Name + "," + dept.Code + ")");
		}
		for(Faculty f: faculties){
			mName2Faculty.put(f.Name, f);
		}
		
		ArrayList<UserInfo> users = new ArrayList<UserInfo>();
		for(StaffInfo st: staffs){
			
			users.add(new UserInfo(st.index,st.email,"e10adc3949ba59abbe56e057f20f883e","salt-sequence",st.email,"1",st.email));
		}
		System.out.println("Filter valid users = " + users.size());
		
		insertUsers(users);
		
		for(StaffInfo st: staffs){
			Department dept = mName2Department.get(st.department);
			if(dept == null){
				System.out.println("department " + st.department + ", not exist");
			}else{
				System.out.println("department " + st.department + ", code = " + dept.Code);
			}
			st.departmentCode = dept.Code;
			Faculty faculty = mName2Faculty.get(st.faculty);
			st.facultyCode = faculty.Code;
		}
		
		insertStaffs(staffs);
		
		ArrayList<UserRole> ur = new ArrayList<UserRole>();
		for(int i = 0; i < users.size(); i++){
			UserInfo ui = users.get(i);
			ur.add(new UserRole(ui.Username,"ROLE_USER"));
		}
		
		insertUserRoles(ur);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StaffUploader SU = new StaffUploader();
		
		SU.initLog();
		SU.upload("ds-can-bo-2015.xls");
		SU.finalize();
	}

}

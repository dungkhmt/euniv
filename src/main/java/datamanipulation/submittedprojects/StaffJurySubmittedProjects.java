package datamanipulation.submittedprojects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import datamanipulation.staffsmanipulation.UserInfo;

public class StaffJurySubmittedProjects {
	public String DRIVER = "com.mysql.jdbc.Driver";
	public String PATH = "jdbc:mysql://localhost/hust_euniv";
	
	public void addAStaffSubmittedProject(String userCode, String projectCode){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "insert into tblstaffjurysubmittedprojects("
					+ "STFJUPRJ_CODE, STFJUPRJ_STAFFJURCODE,STFJUPRJ_PRJCODE) values(?,?,?)";
			PreparedStatement st = cn.prepareStatement(sql);
			st.setString(1,projectCode + "-" + userCode);
			st.setString(2,userCode);
			st.setString(3,projectCode);
			st.execute();
		}catch(Exception ex){
			
		}
	}
	
	public ArrayList<String> getProjectCodes(){
		ArrayList<String> proList = new ArrayList<String>();
		
		try{
		Class.forName(DRIVER);
		Connection cn = DriverManager.getConnection(PATH,"root","");
		Statement st = cn.createStatement();
		String sql = "select * from tblprojects";
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			String code = rs.getString("PROJ_Code");
			proList.add(code);
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return proList;
	}
	public void addStaffSubmittedProjects(ArrayList<String> userCodes){
		try{
			ArrayList<String> proList = getProjectCodes();
			for(String pc: proList){
				for(String uc: userCodes){
					addAStaffSubmittedProject(uc, pc);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> staffs = new ArrayList<String>();
		staffs.add("son.ngohong@hust.edu.vn");
		staffs.add("giang.nguyenlinh@hust.edu.vn");
		staffs.add("lan.nguyenthihoang@hust.edu.vn");
		staffs.add("vinh.lathe@hust.edu.vn");
		staffs.add("duc.nguyenhuu@hust.edu.vn");
		staffs.add("dung.phamquang@hust.edu.vn");
		StaffJurySubmittedProjects A = new StaffJurySubmittedProjects();
		A.addStaffSubmittedProjects(staffs);
	}

}

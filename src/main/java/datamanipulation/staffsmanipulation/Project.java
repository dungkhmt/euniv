package datamanipulation.staffsmanipulation;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Project {
	public String DRIVER = "com.mysql.jdbc.Driver";
	public String PATH = "jdbc:mysql://localhost/hust_euniv";
	public PrintWriter log = null;
	
	public void updateTotalBudget(String projCode){
		int totalBudget = getTotalCostFromTasks(projCode);
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tblprojects where PROJ_Code = '" + projCode + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int budgetMaterial = rs.getInt("PROJ_BudgetMaterial");
				int budget = rs.getInt("PROJ_TotalBudget");
				System.out.println(budgetMaterial + " current TotalBudget = " + budget);
				totalBudget += budgetMaterial;
			}
			
			sql = "update tblprojects set Proj_TotalBudget = " + totalBudget + " where PROJ_Code = '" + projCode + "'";
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.execute();
			
			System.out.println("Update TotalBudget of project " + projCode + ", new TotalBudget = " + totalBudget);			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public int getTotalCostFromTasks(String projCode){
		int totalBudget = 0;
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tblprojecttasks where PRJTSK_Proj_Code = '" + projCode + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int budget = rs.getInt("PRJTSK_Cost");
				System.out.println(budget);
				totalBudget += budget;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return totalBudget;
	}
	public ArrayList<String> getProjectCodes(){
		ArrayList<String> codes = new ArrayList<String>();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tblprojects";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String code = rs.getString("PROJ_Code");
				String staffCode = rs.getString("PROJ_User_Code");
				System.out.println(staffCode + "\t" + code);
				codes.add(code);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return codes;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Project P = new Project();
		ArrayList<String> prjCodes = P.getProjectCodes();
		for(String code: prjCodes){
			P.updateTotalBudget(code);
		}
		//System.out.println(P.getTotalCostFromTasks("T2016101"));
	}

}

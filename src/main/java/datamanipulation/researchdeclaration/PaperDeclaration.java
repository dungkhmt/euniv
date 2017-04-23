package datamanipulation.researchdeclaration;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import datamanipulation.staffsmanipulation.UserInfo;

public class PaperDeclaration {
	public String DRIVER = "com.mysql.jdbc.Driver";
	public String PATH = "jdbc:mysql://localhost/hust_euniv";
	public PrintWriter log = null;

	public void updateUserCodePapers(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tblpapersdeclaration";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				int id = rs.getInt("PDECL_ID");
				String paperCode = rs.getString("PDECL_Code");
				String userCode = rs.getString("PDECL_User_Code");
				int idx = paperCode.indexOf(id + "");
				String newUserCode = "-";
				if(paperCode != "" && !paperCode.equals("")){
					newUserCode = paperCode.substring(0,idx);
					updateUserCodePapers(paperCode, newUserCode);
					System.out.println("paperID = " + id + ", paperCode = " + paperCode + ", newPaperCode = " + newUserCode);
				}
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void updateUserCodePapers(String paperDelarationCode, String userCode){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "update tblpapersdeclaration set PDECL_User_Code = '" + userCode + "' where PDECL_Code = '" + paperDelarationCode + "'";
			Statement st = cn.createStatement();
			st.execute(sql);
			
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaperDeclaration PD = new PaperDeclaration();
		PD.updateUserCodePapers();
	}

}

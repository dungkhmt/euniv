package datamanipulation.commentssubmittedprojects;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CommentSubmittedProjects {
	public String DRIVER = "com.mysql.jdbc.Driver";
	public String PATH = "jdbc:mysql://localhost/hust_euniv";
	public PrintWriter log = null;

	public void removeAll(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "delete from tblcommentssubmittedprojects";
			Statement st = cn.createStatement();
			st.execute(sql);
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

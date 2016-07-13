package datamanipulation.staffsmanipulation;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

public class UserFunctionsManipulation {
	public String DRIVER = "com.mysql.jdbc.Driver";
	public String PATH = "jdbc:mysql://localhost/hust_euniv";
	public PrintWriter log = null;
	
	public ArrayList<UserInfo> getListUsers(String facultyCode){
		ArrayList<UserInfo> users = new ArrayList<UserInfo>();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tblstaffs where Staff_Faculty_Code = '" + facultyCode + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String userCode = rs.getString("Staff_User_Code");
				
				UserInfo u = new UserInfo(0,userCode,"","","","",userCode);
				users.add(u);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return users;
	}
	
	public ArrayList<UserInfo> getListUsers(){
		ArrayList<UserInfo> users = new ArrayList<UserInfo>();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tblusers";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String userCode = rs.getString("Username");
				
				UserInfo u = new UserInfo(0,userCode,"","","","",userCode);
				users.add(u);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return users;
	}
	
	public void removeUserFunction(String userCode){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "delete from tbluserfunctions where USERFUNC_USERCODE = '" + userCode + "'";
			Statement st = cn.createStatement();
			st.execute(sql);
			
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void removeFunctionFromUserFunctions(String functionCode){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			String sql = "delete from tbluserfunctions where USERFUNC_FUNCCODE = '" + functionCode + "'";
			Statement st = cn.createStatement();
			st.execute(sql);
			
			cn.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void removeUserFunctionsExcept(HashSet<String> userCodes){
		ArrayList<UserInfo> users = getListUsers();
		for(int i = 0; i < users.size(); i++){
			String uc = users.get(i).UserCode;
			if(!userCodes.contains(uc)){
				removeUserFunction(uc);
			}
		}
	}
	public void resetUserFunctions(){
		HashSet<String> keepUserCode = new HashSet<String>();
		keepUserCode.add("dung.phamquang@hust.edu.vn");
		removeUserFunctionsExcept(keepUserCode);
	}
	
	public boolean existUserFunction(String userCode, String functionCode){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tbluserfunctions where USERFUNC_FUNCCODE = '" + functionCode + "' and USERFUNC_USERCODE"
					+ " = '" + userCode + "'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				return true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	public void setUserFunction(String userCode, String functionCode){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			
				
				String code = userCode + "_" + functionCode;
				//String sql = "insert into tbluserfunctions(USERFUNC_CODE, USERFUNC_USERCODE,"
				//		+ "USERFUNC_FUNCCODE) values('" + code + "','" + userCode + "','" +
				//		functionCode + "')";
				String sql = "insert into tbluserfunctions(USERFUNC_CODE, USERFUNC_USERCODE,"
								+ "USERFUNC_FUNCCODE) values(?,?,?)";
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1,code);
				st.setString(2, userCode);
				st.setString(3, functionCode);
				st.execute();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public boolean setFunctions(String userCode, ArrayList<String> functionCodes){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			int countSet = 0;
			int countExist = 0;
			for(int i = 0; i < functionCodes.size(); i++){
				String functionCode = functionCodes.get(i);
				if(!existUserFunction(userCode, functionCode)){
					setUserFunction(userCode, functionCode);
					System.out.println("SET user " + userCode + " with function " + functionCode);
					countSet ++;
				}else{
					System.out.println("user " + userCode + " HAS already function " + functionCode);
					countExist++;
				}
				Thread.sleep(500);
			}
			return countSet > 0;
			/*
			for(int i = 0; i < functionCodes.size(); i++){
				String functionCode = functionCodes.get(i);
				String code = userCode + "_" + functionCode;
				//String sql = "insert into tbluserfunctions(USERFUNC_CODE, USERFUNC_USERCODE,"
				//		+ "USERFUNC_FUNCCODE) values('" + code + "','" + userCode + "','" +
				//		functionCode + "')";
				String sql = "insert into tbluserfunctions(USERFUNC_CODE, USERFUNC_USERCODE,"
								+ "USERFUNC_FUNCCODE) values(?,?,?)";
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1,code);
				st.setString(2, userCode);
				st.setString(3, functionCode);
				st.execute();
			}
			*/
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	public HashSet<String> getUserCodeFromUSerFunctions(){
		HashSet<String> userCodes = new HashSet<String>();
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tbluserfunctions";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				String userCode = rs.getString("USERFUNC_USERCODE");
				String functionCode = rs.getString("USERFUNC_FUNCCODE");
				//System.out.println(userCode + "\t" + functionCode);
				userCodes.add(userCode);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return userCodes;
	}
	public ArrayList<UserFunction> getListUserFunctions(){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			Statement st = cn.createStatement();
			String sql = "select * from tbluserfunctions";
			ResultSet rs = st.executeQuery(sql);
			ArrayList<UserFunction> L = new ArrayList<UserFunction>();
			while(rs.next()){
				String userCode = rs.getString("USERFUNC_USERCODE");
				String functionCode = rs.getString("USERFUNC_FUNCCODE");
				//System.out.println(userCode + "\t" + functionCode);
				L.add(new UserFunction(userCode, functionCode));
			}
			return L;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	private boolean exist(ArrayList<UserFunction> L, String u, String f){
		for(UserFunction uf: L){
			if(uf.userCode.equals(u) && uf.functionCode.equals(f)) return true;
		}
		return false;
	}
	public void setUserFunctions(ArrayList<UserFunction> UF){
		try{
			Class.forName(DRIVER);
			Connection cn = DriverManager.getConnection(PATH,"root","");
			for(int i = 0; i < UF.size(); i++){
				UserFunction uf = UF.get(i);
				
				String code = uf.userCode + "_" + uf.functionCode;
				//String sql = "insert into tbluserfunctions(USERFUNC_CODE, USERFUNC_USERCODE,"
				//		+ "USERFUNC_FUNCCODE) values('" + code + "','" + userCode + "','" +
				//		functionCode + "')";
				String sql = "insert into tbluserfunctions(USERFUNC_CODE, USERFUNC_USERCODE,"
								+ "USERFUNC_FUNCCODE) values(?,?,?)";
				PreparedStatement st = cn.prepareStatement(sql);
				st.setString(1,code);
				st.setString(2, uf.userCode);
				st.setString(3, uf.functionCode);
				st.execute();
				
				Thread.sleep(200);
				System.out.println("setUserFunction, finished " + i + "/" + UF.size());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void setUserFunctions(String facultyCode){
		ArrayList<UserFunction> L = getListUserFunctions();
		
		ArrayList<UserInfo> users = getListUsers(facultyCode);
		
		ArrayList<String> functions = new ArrayList<String>();
		functions.add("MANAGE-PAPERS");
		functions.add("PAPERS-DECLARATION-MANAGEMENT");
		functions.add("MANAGE-TOPICS");
		functions.add("MANAGE-PATENTS");
		functions.add("SAMPLE-01CN-02CN");
		
		ArrayList<UserFunction> newUserFunction = new ArrayList<UserFunction>();
		int count = 0;
		for(int i = 0; i < users.size(); i++){
			String userCode = users.get(i).UserCode;
			for(int j = 0; j < functions.size(); j++){
				String functionCode = functions.get(j);
				System.out.println("setUserFunctions, i = " + i + ", j = " + j + ", consider user " + userCode + ", function " + functionCode);
				if(!exist(L, userCode, functionCode)){
					newUserFunction.add(new UserFunction(userCode, functionCode));
				}
			}
		}
		System.out.println("setUserFunctions, new user-functions = " + newUserFunction.size());
		setUserFunctions(newUserFunction);
		
	
		
	}
	public void setUserFunctions(){
		ArrayList<UserFunction> L = getListUserFunctions();
		
		ArrayList<UserInfo> users = getListUsers();
		
		ArrayList<String> functions = new ArrayList<String>();
		functions.add("MANAGE-PAPERS");
		functions.add("PAPERS-DECLARATION-MANAGEMENT");
		functions.add("SAMPLE-01CN-02CN");
		
		ArrayList<UserFunction> newUserFunction = new ArrayList<UserFunction>();
		int count = 0;
		for(int i = 0; i < users.size(); i++){
			String userCode = users.get(i).UserCode;
			for(int j = 0; j < functions.size(); j++){
				String functionCode = functions.get(j);
				System.out.println("setUserFunctions, L.sz = " + L.size() + ", i = " + i + ", j = " + j + ", consider user " + userCode + ", function " + functionCode);
				if(!exist(L, userCode, functionCode)){
					newUserFunction.add(new UserFunction(userCode, functionCode));
				}
			}
		}
		System.out.println("setUserFunctions, new user-functions = " + newUserFunction.size());
		setUserFunctions(newUserFunction);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserFunctionsManipulation UFM = new UserFunctionsManipulation();
		UFM.setUserFunctions("SOICT");
		//UFM.setUserFunctions();
		//UFM.removeFunctionFromUserFunctions("REVIEW-SUBMITTED-PROJECTS");
		//UFM.resetUserFunctions();
		//System.out.println(UFM.existUserFunction("dung.phamquang@hust.edu.vn", "STUDENTS-MANAGEMENT"));
		System.out.println("DONE.....");
	}

}

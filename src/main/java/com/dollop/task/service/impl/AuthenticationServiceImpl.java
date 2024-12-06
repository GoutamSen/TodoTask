package com.dollop.task.service.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dollop.task.connection.DatabaseConnection;
import com.dollop.task.entities.LoginResponse;
import com.dollop.task.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

	 private final DatabaseConnection db = new DatabaseConnection();
	 private Connection conn = null;
	    @Override
	    public LoginResponse login(String username, String password) throws ClassNotFoundException, SQLException {
            boolean loginSuccess = false;
            LoginResponse loginResponse=null;
	        Integer id = null;
	        System.out.println(username+""+password);
	        String query = "SELECT * FROM accounts WHERE username=? AND password=?";
	        conn = db.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {	        	 
	            id = rs.getInt("id");
	            loginSuccess=true;
	            return new LoginResponse(loginSuccess, "USER",id); // Returning true for successful login
	        }	        
	        else {	           	            
	        	String adminQuery = "SELECT * FROM admins WHERE adminname=? AND password=?";
	            PreparedStatement pstmtd = conn.prepareStatement(adminQuery);
	            pstmtd.setString(1, username);
	            pstmtd.setString(2, password);
	            ResultSet rss = pstmtd.executeQuery();	            
	            if (rss.next()) {
	                id = rss.getInt("id");
	                String firstName=rs.getString("password");
		            System.out.println(firstName);
	                loginSuccess = true;
	                return new LoginResponse(loginSuccess, "ADMIN",id);
	            }
	        }
	       if(!loginSuccess) {
	    	    System.out.println("Login Failed : ");
	       }
	        return new LoginResponse(loginSuccess,"Login Failed",0);
	    }
}

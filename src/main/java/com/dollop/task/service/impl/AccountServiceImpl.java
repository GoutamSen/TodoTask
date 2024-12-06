package com.dollop.task.service.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.dollop.task.connection.DatabaseConnection;
import com.dollop.task.entities.Account;
import com.dollop.task.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private final DatabaseConnection db = new DatabaseConnection();
    private Connection conn=null;

	@Override
	public boolean createUser(Account account) throws ClassNotFoundException, SQLException {
	  conn = db.getConnection();
      String query = "insert into accounts(userName, firstName, lastName, password, createdAt, adminId,statusid) value(?,?,?,?,?,?,?)";
      PreparedStatement pstmt = conn.prepareStatement(query);
      pstmt.setString(1, account.getUserName());
      pstmt.setString(2, account.getFirstName());
      pstmt.setString(3, account.getLastName());
      pstmt.setString(4, account.getPassword());
      pstmt.setTimestamp(5, account.getCreatedAt());
      pstmt.setInt(6, account.getAdminId());
      pstmt.setInt(7, account.getStatusId());
      
      int affectedRow = pstmt.executeUpdate();
      if(affectedRow>0) {
          return true;
      }
      else {
    	  return false;
      }
	}

	
	@Override
	public boolean updateUser(int id, Account account) throws ClassNotFoundException, SQLException {
		conn = db.getConnection();
	    String query = "UPDATE accounts SET  firstName=?, lastName=?,statusid=? WHERE id=?";
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setString(1, account.getFirstName());
	    pstmt.setString(2, account.getLastName());
	    pstmt.setInt(3, account.getStatusId()); 
	    pstmt.setInt(4, id); 
	    try {
	         int affectedRow = pstmt.executeUpdate(); // Execute the update query
	        if(affectedRow>0) {
	        	return true;        	
	        }
	       
	    } catch (SQLException e) {
	        System.err.println("SQL error occurred: " + e.getMessage());
	        e.printStackTrace(); // To get more detailed error info
	    }
	 return false;   
	}

	@Override
	public Account getUser(int id) throws SQLException, ClassNotFoundException {
		conn= db.getConnection();
		Account account = new Account();
		String query = "select * from accounts where id=?";
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {			
			int user_id = rs.getInt("id");
			String user_name=rs.getString("username");
			String user_first_name=rs.getString("firstname");
			String user_last_name=rs.getString("lastname");
			String user_password=rs.getString("password");
			Timestamp user_created=rs.getTimestamp("createdAt");
			int adminId = rs.getInt("adminId");
			int statusId = rs.getInt("statusId");
			 account = new Account(id, user_name, user_first_name, user_last_name, user_password, user_created, adminId, statusId);
		}
		return account;
	}
		

	@Override
	public List<Account> getAllUserByUserId(Integer userId) throws SQLException, ClassNotFoundException {
		Account account = new Account();
		ArrayList<Account> list = new ArrayList<>();
		String query = "select * from accounts where adminId=?";
		conn = db.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, userId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			int user_id = rs.getInt("id");
			String user_name=rs.getString("username");
			String user_first_name=rs.getString("firstname");
			String user_last_name=rs.getString("lastname");
			String user_password=rs.getString("password");
			Timestamp user_created=rs.getTimestamp("createdAt");
			int adminId = rs.getInt("adminId");
			int statusId = rs.getInt("statusId");
		    account = new Account(user_id, user_name, user_first_name, user_last_name, user_password, user_created,adminId,statusId);
			list.add(account);
		}
		return list;
	}


	@Override
	public int getIdByUserStatus(String status) {
       String query = "select id from status where status=?";
       try {
		conn = db.getConnection();
		PreparedStatement pstmt=conn.prepareStatement(query);
		pstmt.setString(1, status);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			return rs.getInt("id");
		}
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	} 
		return 0;
	}


	@Override
	public String getStatusById(int id) {

		String query = "select status from status where id=?";
	       try {
			conn = db.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getString("status");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}


	@Override
	public Account getUserByUserName(String username) {
		try {
			conn= db.getConnection();
			Account account = new Account();
			String query = "select * from accounts where username=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {			
				int user_id = rs.getInt("id");
				String user_name=rs.getString("username");
				String user_first_name=rs.getString("firstname");
				String user_last_name=rs.getString("lastname");
				String user_password=rs.getString("password");
				Timestamp user_created=rs.getTimestamp("createdAt");
				int adminId = rs.getInt("adminId");
				int statusId = rs.getInt("statusId");
				account = new Account(user_id, user_name, user_first_name, user_last_name, user_password, user_created, adminId, statusId);				
			}
			return account;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
}

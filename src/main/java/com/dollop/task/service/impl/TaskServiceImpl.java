package com.dollop.task.service.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dollop.task.connection.DatabaseConnection;
import com.dollop.task.entities.Task;
import com.dollop.task.service.TaskService;

public class TaskServiceImpl implements TaskService {

	private final DatabaseConnection db = new DatabaseConnection();
    private Connection conn;
	
	@Override
	public boolean createTask(Task task) throws ClassNotFoundException, SQLException {
		  conn = db.getConnection();
	      String query = "insert into todotasks(accountId,statusId,priorityId,details,createdAt,deadline,lastupdate) values(?,?,?,?,?,?,?)";
	      PreparedStatement pstmt = conn.prepareStatement(query);	      
	      pstmt.setInt(1, task.getAccountId());
	      pstmt.setInt(2, task.getStatusId());
	      pstmt.setInt(3, task.getPriorityId());
	      pstmt.setString(4, task.getDetails());
	      pstmt.setTimestamp(5, task.getCreatedAt());
	      pstmt.setTimestamp(6, task.getDeadline());
	      pstmt.setTimestamp(7, task.getLastUpdate());
	      int affectedRow = pstmt.executeUpdate();
	      System.out.println("affected");
	      if(affectedRow<=0) {
	    	 return false;
	      }
	      else {
	    	 return true;
	      }
	}

	@Override
	public boolean updateTask(int id, Task task) throws ClassNotFoundException, SQLException {
		  conn=db.getConnection();
	      String query = "update todotasks set details=?,deadline=?,lastupdate=? where id=?";
	      PreparedStatement pstmt=conn.prepareStatement(query);	      
	      pstmt.setString(1, task.getDetails());
	      pstmt.setTimestamp(2, task.getDeadline());
	      pstmt.setTimestamp(3, task.getLastUpdate());
	      pstmt.setInt(4, id);	      
	      int affectedRow = pstmt.executeUpdate();      
	      if(affectedRow<=0) {
	    	  return false;
	      }
	      else {
	    	  return true;
	      }		
	}

	@Override
	public Task getTask(int id) throws SQLException, ClassNotFoundException {
		String query = "select * from todotasks where id=?";
		conn=db.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		Task task = new Task();
		while(rs.next()) {
			task.setId(id);
			task.setDetails(rs.getString("details"));
			task.setCreatedAt(rs.getTimestamp("createdAt"));
			task.setDeadline(rs.getTimestamp("deadline"));
			task.setLastUpdate(rs.getTimestamp("lastupdate"));
			task.setAccountId(rs.getInt("accountid"));
			task.setPriorityId(rs.getInt("priorityid"));
			task.setStatusId(rs.getInt("statusid"));
		}
	   return task;
	}



	
	@Override
	public List<Task> getAllTaskByUser(int id) throws ClassNotFoundException, SQLException {		
	    String query ="select * from todotasks where accountId=?";
	    List<Task> listOfTask = new ArrayList<>();  // Ensure the list is initialized.
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    try {
	        conn = db.getConnection();  // Get the database connection.
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            Task task = new Task();
	            task.setId(rs.getInt("id"));
	            task.setDetails(rs.getString("details"));
	            task.setCreatedAt(rs.getTimestamp("createdAt"));
	            task.setDeadline(rs.getTimestamp("deadline"));
	            task.setLastUpdate(rs.getTimestamp("lastupdate"));
	            task.setAccountId(rs.getInt("accountId"));
	            task.setStatusId(rs.getInt("statusId"));
	            task.setPriorityId(rs.getInt("priorityid"));
	            listOfTask.add(task);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 

	    return listOfTask;
	}

	@Override
	public String getStatusByTask(int id) {
	 String query = "select status from taststatus where id=?";
       try {
		conn= db.getConnection();
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
	public String getPriorityByTask(int id) {
	       String query = "select priority from taskpriorities where id=?";
	       try {
			conn= db.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("ddd");
				return rs.getString("priority");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
			return null;
	}

	@Override
	public int getIdByStatus(String status) {
		String query="select id from taststatus where status=?";
		try {
			conn=db.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, status);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("id"));
				return rs.getInt("id");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getIdByPriority(String priority) {
		String query="select id from taskpriorities where priority=?";
		try {
			conn=db.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, priority);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getInt("id");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}

package com.dollop.task.service.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.dollop.task.connection.DatabaseConnection;
import com.dollop.task.entities.Session;
import com.dollop.task.service.SessionService;

public class SessionServiceImpl implements SessionService {
	
	private final DatabaseConnection db = new DatabaseConnection();
	private Connection conn = null;

	@Override
	public boolean createSession(int accountId, Session session) {
			String query="insert into accountsession(id,accountid,createdAt,endAt) values(?,?,?,?)";			
				try {
					conn = db.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, session.getId());
					pstmt.setInt(2, accountId);
					pstmt.setTimestamp(3, session.getCreatedAt());
					pstmt.setTimestamp(4, session.getEndedAt());
					int affectedRow = pstmt.executeUpdate();
					if(affectedRow>0) {
						return true;
					}					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}		
		return false;
	}

	@Override
	public boolean updateSession(int accountId, Session session) {
		String query="update accountsession set endedAt=? where id=?";			
			try {
				conn = db.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setTimestamp(1, session.getEndedAt());
				pstmt.setInt(2, session.getId());
				int affectedRow = pstmt.executeUpdate();
				if(affectedRow>0) {
					return true;
				}					
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}		
	return false;
	}	
}

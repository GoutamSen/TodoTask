package com.dollop.task.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	 public static final String URL_DB="jdbc:mysql://localhost:3306/newtodotask";
	 public static final String URL_NAME="root";
	 public static final String URL_PASSWORD="root";
	 public static Connection conn;
	 
	 public Connection getConnection() throws ClassNotFoundException, SQLException {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 conn=DriverManager.getConnection(URL_DB,URL_NAME,URL_PASSWORD);
          return conn;
	 }
	 
}

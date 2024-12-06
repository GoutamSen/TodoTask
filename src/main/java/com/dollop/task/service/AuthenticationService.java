package com.dollop.task.service;
import java.sql.SQLException;
import com.dollop.task.entities.LoginResponse;

public interface AuthenticationService {

	LoginResponse login(String username,String password) throws ClassNotFoundException, SQLException;
	
}

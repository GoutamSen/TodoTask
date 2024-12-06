package com.dollop.task.service;
import java.sql.SQLException;
import java.util.List;
import com.dollop.task.entities.Account;

public interface AccountService {

      boolean createUser(Account account) throws ClassNotFoundException, SQLException;
      boolean updateUser(int id,Account account) throws ClassNotFoundException, SQLException;
      Account getUser(int id) throws SQLException, ClassNotFoundException;
      Account getUserByUserName(String username);
      List<Account> getAllUserByUserId(Integer userId) throws SQLException, ClassNotFoundException;
      int getIdByUserStatus(String status);
      String getStatusById(int id);
}

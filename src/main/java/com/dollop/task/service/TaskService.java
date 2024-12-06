package com.dollop.task.service;
import java.sql.SQLException;
import java.util.List;
import com.dollop.task.entities.Task;
public interface TaskService {

	boolean createTask(Task task) throws ClassNotFoundException, SQLException;
	boolean updateTask(int id,Task task) throws ClassNotFoundException, SQLException;
	Task getTask(int id) throws SQLException, ClassNotFoundException;
	List<Task> getAllTaskByUser(int id) throws ClassNotFoundException, SQLException;
	String getStatusByTask(int id);
	String getPriorityByTask(int id);
	int getIdByStatus(String status);
	int getIdByPriority(String priority);
}

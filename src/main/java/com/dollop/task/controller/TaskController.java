package com.dollop.task.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dollop.task.entities.Task;
import com.dollop.task.service.TaskService;
import com.dollop.task.service.impl.TaskServiceImpl;

public class TaskController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final TaskService service = new TaskServiceImpl();
	private static final String ACTION_ADD="add";
	private static final String ACTION_UPDATE="update";
	private static final String USER_DASHBOARD_PATH="./user-dashboard.jsp";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        String action = request.getParameter("action");
	        switch (action) {
	            case ACTION_ADD:
	                int userId = Integer.parseInt(request.getParameter("id"));
	                handleAddTask(userId, request, response);
	                break;
	            case ACTION_UPDATE:
	                int taskId = Integer.parseInt(request.getParameter("taskid"));
	                handleEditAccount(request, response, taskId);
	                break;
	            default:
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Action");
	                break;
	        }
	    }catch (Exception e) {
            e.printStackTrace();
	    }
	}

	protected void handleAddTask(int id , HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException {
          try {
        	  String status=request.getParameter("choiceInput");
        	  String priority = request.getParameter("choicePriority");
        	  String dateInput = request.getParameter("dateInput");		
			  String details=request.getParameter("details");

			  if (details == null || status == null || priority == null || dateInput == null) {
		            throw new IllegalArgumentException("All input fields are required.");
		        }
        	  
        	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        	  dateFormat.setLenient(false);      	         	  
        	  Date date;
  	        try {
  	           date = dateFormat.parse(dateInput);
  	        } catch (ParseException e) {
  	            System.out.println("Invalid date format: " + e.getMessage());
  	            e.printStackTrace();  
  	            throw new ServletException("Invalid date format.");
  	        }			 		  
			  Timestamp deadlinee = new Timestamp(date.getTime());
			  int statusid=service.getIdByStatus(status);
			  int priorityid=service.getIdByPriority(priority);
			  Timestamp createdAt=new Timestamp(System.currentTimeMillis());
			  Timestamp updatedAt=new Timestamp(System.currentTimeMillis());		  
			  Task task=createTask(id,details, createdAt, updatedAt, deadlinee, statusid, priorityid);			  
			  boolean isTaskCreated =  service.createTask(task);
			  if(isTaskCreated) {					 
				 List<Task> listOfTask = service.getAllTaskByUser(id);
	            	request.setAttribute("tasks", listOfTask);
	            	request.setAttribute("id", id);
	            	RequestDispatcher rd = request.getRequestDispatcher(USER_DASHBOARD_PATH);
	            	rd.include(request, response);	            	
			 }
		} catch (Exception e) {
			e.printStackTrace();
	}      		  
}
	
	protected void handleEditAccount(HttpServletRequest request, HttpServletResponse response, int id)
	        throws ClassNotFoundException, SQLException, ServletException, IOException {
	    try {
	        Task oldTask = service.getTask(id);
	        String details = request.getParameter("details");
	        String status = request.getParameter("choiceInput");
	        String priority = request.getParameter("choicePriority");
	        String dateInput = request.getParameter("dateInput");

	        if (details == null || status == null || priority == null || dateInput == null) {
	            throw new IllegalArgumentException("All input fields are required.");
	        }
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false); 

	        Date date;
	        try {
	            date = dateFormat.parse(dateInput);
	        } catch (ParseException e) {
	            System.out.println("Invalid date format: " + e.getMessage());
	            e.printStackTrace();  
	            throw new ServletException("Invalid date format.");
	        }

	        Timestamp deadline = new Timestamp(date.getTime());
	        Timestamp lastUpdatedAt = new Timestamp(System.currentTimeMillis());

	        int statusId = service.getIdByStatus(status);
	        int priorityId = service.getIdByPriority(priority);	        
	        Task task = createTask(oldTask.getAccountId(),details,oldTask.getCreatedAt(),lastUpdatedAt,
	                    deadline,statusId,priorityId);
	        boolean isTaskUpdated = service.updateTask(id, task);
            if (isTaskUpdated) {
	            List<Task> listOfTask = service.getAllTaskByUser(task.getAccountId());
	            request.setAttribute("tasks", listOfTask);
	            request.setAttribute("id", task.getAccountId());
	            RequestDispatcher rd = request.getRequestDispatcher(USER_DASHBOARD_PATH);
	            rd.include(request, response);
	        } else {
	            System.out.println("Task update failed.");
	        }

	    } catch (IllegalArgumentException e) {
	        System.out.println("Error: " + e.getMessage());
	        e.printStackTrace(); 
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Unexpected error: " + e.getMessage());
	        e.printStackTrace();  
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error.");
	    }
	}

	
	private Task createTask(int accountid,String details,Timestamp createdAt,Timestamp lastUpdatedAt,Timestamp deadline,int statusId,int priorityId) {
		Task createdTask = new Task();
		createdTask.setAccountId(accountid);
		createdTask.setDetails(details);
		createdTask.setCreatedAt(createdAt);
		createdTask.setLastUpdate(lastUpdatedAt);
		createdTask.setDeadline(deadline);
		createdTask.setStatusId(statusId);
		createdTask.setPriorityId(priorityId);
		return createdTask;
	}
	
}

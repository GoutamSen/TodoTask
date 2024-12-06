package com.dollop.task.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dollop.task.entities.Account;
import com.dollop.task.entities.LoginResponse;
import com.dollop.task.entities.Session;
import com.dollop.task.entities.Task;
import com.dollop.task.service.AccountService;
import com.dollop.task.service.AuthenticationService;
import com.dollop.task.service.SessionService;
import com.dollop.task.service.TaskService;
import com.dollop.task.service.impl.AccountServiceImpl;
import com.dollop.task.service.impl.AuthenticationServiceImpl;
import com.dollop.task.service.impl.SessionServiceImpl;
import com.dollop.task.service.impl.TaskServiceImpl;

public class AuthenticationController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String Admin_Dashboard_Path = "./admin-dashboard.jsp";
	private static final String User_Dashboard_Path = "./user-dashboard.jsp";
	private static final String Login_Page_Path = "./login.jsp";
	
	private final AuthenticationService authenticationService = new AuthenticationServiceImpl();
	private final AccountService accountService = new AccountServiceImpl();
	private final TaskService taskService = new TaskServiceImpl();
	private final SessionService sessionService = new SessionServiceImpl();
	
	
	protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {	
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println(username+"  "+password);
		try {
			LoginResponse loginResponse  = authenticationService.login( username, password);
			System.out.println(loginResponse+" loginResponse-");
      			if(loginResponse.isSuccess()&&loginResponse.getRole().equalsIgnoreCase("ADMIN")) {
      			HttpSession session = request.getSession();
      			session.setAttribute("username", username);
      		    session.setMaxInactiveInterval(1800);              		    
      		    System.out.println("session is created : ");
      		    //30 minutes
            	List<Account> listOfAccount = accountService.getAllUserByUserId(loginResponse.getUserId());
            	request.setAttribute("accounts", listOfAccount);
          
            	System.out.println(listOfAccount+ " list of accounts+++");
            	request.setAttribute("id", loginResponse.getUserId());
            	request.setAttribute("loginSuccess", true);
            	
            	RequestDispatcher rd = request.getRequestDispatcher(Admin_Dashboard_Path);
            	rd.include(request, response);        	
            	
            	try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
            }
            else if(loginResponse.isSuccess()&&loginResponse.getRole().equalsIgnoreCase("USER")) {           	    
            	Account account = accountService.getUser(loginResponse.getUserId());
            	String status = accountService.getStatusById(account.getStatusId());	
            	 if(status.equalsIgnoreCase("TRUE")) {
            		HttpSession session = request.getSession();
            		session.setAttribute("username", username);
            		session.setAttribute("userId",  loginResponse.getUserId());
            		session.setMaxInactiveInterval(1800);
            		if(session!=null) {
            			Session createSession = new Session();
            			createSession.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            		    boolean isSessionCreated = sessionService.createSession(loginResponse.getUserId(), createSession);
            		  if(isSessionCreated) {
            			 List<Task> listOfTask = taskService.getAllTaskByUser(loginResponse.getUserId());           	 
                		
            			 request.setAttribute("tasks", listOfTask);               		 
                		 request.setAttribute("id", loginResponse.getUserId()); 
                		 request.setAttribute("loginSuccess", true);
                		 RequestDispatcher rd = request.getRequestDispatcher(User_Dashboard_Path);
                		 rd.include(request, response);
            		}	 
            	  }           		
            	 }
            	 else {
            		 request.setAttribute("Block",true);
               	     RequestDispatcher rd = request.getRequestDispatcher(Login_Page_Path);
               	     rd.include(request, response);
            	 }      	 
            }
            else {            
            	  request.setAttribute("InvalidInformation",true);
            	  RequestDispatcher rd = request.getRequestDispatcher(Login_Page_Path);
            	  rd.include(request, response);
                 }						
		} catch (ClassNotFoundException | SQLException | ServletException e) {
			e.printStackTrace();
		} 
	}
	
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws IOException {	
       
       // Get the current session, if it exists
       HttpSession session = request.getSession(false);
       if(session!=null) {    	   
    	   String username = (String) session.getAttribute("username");   	   
    	   session.invalidate();
       }
       response.sendRedirect("login.jsp");
	}
}

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
import com.dollop.task.entities.Account;
import com.dollop.task.service.AccountService;
import com.dollop.task.service.impl.AccountServiceImpl;

public class AccountController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final AccountService service = new AccountServiceImpl();
    private static final String ACTION_ADD="add";
    private static final String ACTION_UPDATE="update";
	private static final String ADMIN_DASHBOARD_PATH="./admin-dashboard.jsp";
	private static final String REGISTRATION_PAGE_PATH="./register.jsp";
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {	 
		try {			
			String action = request.getParameter("action");
			if(action==null||action.trim().isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Action parameter is required");
			}	
			Integer adminId = Integer.parseInt(request.getParameter("id"));			
			switch(action.toLowerCase()) {
			case ACTION_ADD : {
	                            addAccount(request,response ,adminId);	
	                          }
			                  break;	  
			case ACTION_UPDATE:{
				                 editAccount(adminId,request, response);
				               } 
			                   break;
		    default : response.sendError(HttpServletResponse.SC_BAD_REQUEST , "Invalid Action"+ action);
		   }		
		}
		catch(Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"An Unexcepted error occured");
	}
}
	
	protected void addAccount(HttpServletRequest request,HttpServletResponse response,Integer adminId) throws ClassNotFoundException, SQLException {         		    
		Account account = extractAccountFromRequest(request, adminId, false);
		  boolean isAccountRegister = service.createUser(account);
		  if(isAccountRegister){
				List<Account> listOfAccount = service.getAllUserByUserId(account.getAdminId());
            	request.setAttribute("accounts", listOfAccount);
            	RequestDispatcher rd = request.getRequestDispatcher(ADMIN_DASHBOARD_PATH);
            	try {
					rd.include(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				} 
		  }
		  else {
			   RequestDispatcher rd = request.getRequestDispatcher(REGISTRATION_PAGE_PATH);
		   }
	}
	
	protected void editAccount(Integer id, HttpServletRequest request,HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {         		   
           Account account = extractAccountFromRequest(request, 0, false);
    	   boolean isUpdateAccount = service.updateUser(id, account);
    		if(isUpdateAccount){
    			List<Account> listOfAccount = service.getAllUserByUserId(account.getAdminId());
            	request.setAttribute("accounts", listOfAccount);
            	RequestDispatcher rd = request.getRequestDispatcher(ADMIN_DASHBOARD_PATH);           	
            	try {
					rd.include(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
    		}
    		else {
    	          request.setAttribute("errorMessage", "Failed to update user. Please try again.");
         }	
	}
	
	private Account createAccount(String username,String firstName,String lastName,String password,Timestamp createdAt,int adminId,int statusId) {
		Account createAccount = new Account();
		createAccount.setUserName(username);
		createAccount.setFirstName(firstName);
		createAccount.setLastName(lastName);
		createAccount.setPassword(password);          
		createAccount.setCreatedAt(createdAt);
		createAccount.setAdminId(adminId);       
		createAccount.setStatusId(statusId);
		return createAccount;
	}
	
	 private Account extractAccountFromRequest(HttpServletRequest request, int adminId, boolean isNew) {
	        String username = isNew ? request.getParameter("userName") : null;
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String password = isNew ? request.getParameter("password") : null;
	        Timestamp createdAt = isNew ? new Timestamp(System.currentTimeMillis()) : null;
	        String status = request.getParameter("choiceStatus");
	        int statusId = service.getIdByUserStatus(status);
	        return createAccount(username, firstName, lastName, password, createdAt, adminId, statusId);
	    }
}

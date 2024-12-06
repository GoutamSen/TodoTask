package com.dollop.task.entities;
import java.sql.Timestamp;

public class Account {

	private int accountId;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private Timestamp createdAt;
	private int adminId;
	private int statusId;
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Account(int accountId, String userName, String firstName, String lastName, String password,
			Timestamp createdAt, int adminId, int statusId) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.createdAt = createdAt;
		this.adminId = adminId;
		this.statusId = statusId;
	}

	
	

	/**
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}


	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the createdAt
	 */
	public Timestamp getCreatedAt() {
		return createdAt;
	}


	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	/**
	 * @return the adminId
	 */
	public int getAdminId() {
		return adminId;
	}


	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}


	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}


	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}


	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + ", createdAt=" + createdAt + ", adminId=" + adminId
				+ ", statusId=" + statusId + "]";
	}
	
	
	
}

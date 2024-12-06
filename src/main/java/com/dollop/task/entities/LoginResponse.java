package com.dollop.task.entities;

public class LoginResponse {

	private boolean success;
	private String role;
	private int userId;
	
	public LoginResponse(boolean success, String role ,int userId) {
		this.success = success;
		this.role = role;
		this.userId=userId;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getUserId() {
		return userId;
	}
	
	@Override
	public String toString() {
		return "LoginResponse [success=" + success + ", role=" + role + ", userId=" + userId + "]";
	}
	
	
}

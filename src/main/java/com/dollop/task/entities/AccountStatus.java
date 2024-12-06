package com.dollop.task.entities;

public class AccountStatus {

	private int id;
	private String status;
	
	public AccountStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public AccountStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}

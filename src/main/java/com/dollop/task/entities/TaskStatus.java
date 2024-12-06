package com.dollop.task.entities;

public class TaskStatus {

	private int id;
	private String status;
	
	public TaskStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}
	public TaskStatus() {
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

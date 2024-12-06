package com.dollop.task.entities;

public class TaskPriorities {

	
	private int id;
	private String priority;
	
	public TaskPriorities(int id, String priority) {
		super();
		this.id = id;
		this.priority = priority;
	}

	public TaskPriorities() {
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
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	
}

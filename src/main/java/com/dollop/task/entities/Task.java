package com.dollop.task.entities;
import java.sql.Timestamp;


public class Task {

	private int id;
	private String details;
	private Timestamp createdAt;
	private Timestamp deadline;
	private Timestamp lastUpdate;
	private int accountId;
	private int statusId;
	private int priorityId;
	
	
	
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
	/**
	 * @return the priorityId
	 */
	public int getPriorityId() {
		return priorityId;
	}
	/**
	 * @param priorityId the priorityId to set
	 */
	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}
	public Task(int id, String details, Timestamp createdAt, Timestamp deadline, Timestamp lastUpdate, int accountId,
			int statusId, int priorityId) {
		super();
		this.id = id;
		this.details = details;
		this.createdAt = createdAt;
		this.deadline = deadline;
		this.lastUpdate = lastUpdate;
		this.accountId = accountId;
		this.statusId = statusId;
		this.priorityId = priorityId;
	}
	public Task() {
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
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
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
	 * @return the deadline
	 */
	public Timestamp getDeadline() {
		return deadline;
	}
	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	/**
	 * @return the lastUpdate
	 */
	public Timestamp getLastUpdate() {
		return lastUpdate;
	}
	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
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
	@Override
	public String toString() {
		return "Task [id=" + id + ", details=" + details + ", createdAt=" + createdAt + ", deadline=" + deadline
				+ ", lastUpdate=" + lastUpdate + ", accountId=" + accountId + ", statusId=" + statusId + ", priorityId="
				+ priorityId + "]";
	}
	
	
}

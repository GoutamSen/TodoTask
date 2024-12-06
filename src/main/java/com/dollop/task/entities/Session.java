package com.dollop.task.entities;
import java.sql.Timestamp;

public class Session {

	private int id;
	private Timestamp createdAt;
	private Timestamp endedAt;
	
	public Session() {
		super();
	}

	public Session(int id, Timestamp createdAt, Timestamp endedAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.endedAt = endedAt;
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
	 * @return the endedAt
	 */
	public Timestamp getEndedAt() {
		return endedAt;
	}

	/**
	 * @param endedAt the endedAt to set
	 */
	public void setEndedAt(Timestamp endedAt) {
		this.endedAt = endedAt;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", createdAt=" + createdAt + ", endedAt=" + endedAt + "]";
	}
	
	
}

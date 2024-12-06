package com.dollop.task.entities;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccountSession {

	private String id;
	private LocalDateTime createdAt ;
	private LocalDate endAt;
	private Account account;
	
	public AccountSession(String id, LocalDateTime createdAt, LocalDate endAt, Account account) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.endAt = endAt;
		this.account = account;
	}
	public AccountSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the endAt
	 */
	public LocalDate getEndAt() {
		return endAt;
	}
	/**
	 * @param endAt the endAt to set
	 */
	public void setEndAt(LocalDate endAt) {
		this.endAt = endAt;
	}
	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}

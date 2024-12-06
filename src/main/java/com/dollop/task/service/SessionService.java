package com.dollop.task.service;

import com.dollop.task.entities.Session;

public interface SessionService {

	boolean createSession(int accountId,Session session);
	boolean updateSession(int accountId, Session session);
}
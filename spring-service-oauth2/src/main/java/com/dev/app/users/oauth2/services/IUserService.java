package com.dev.app.users.oauth2.services;

import com.dev.app.users.commons.models.User;

public interface IUserService {

	public User findByUsername(String username);
	
	public User update(User user, Long id);
}

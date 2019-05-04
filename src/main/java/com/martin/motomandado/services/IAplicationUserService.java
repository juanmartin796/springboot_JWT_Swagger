package com.martin.motomandado.services;

import com.martin.motomandado.model.User;

public interface IAplicationUserService {
	public User findByUsername(String username);
	public boolean existsByUsername(String username);
	public void save(User user);
}

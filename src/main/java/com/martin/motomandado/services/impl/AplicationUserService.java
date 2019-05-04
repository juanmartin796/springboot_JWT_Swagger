package com.martin.motomandado.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martin.motomandado.dao.IAplicationUserDao;
import com.martin.motomandado.model.User;
import com.martin.motomandado.services.IAplicationUserService;

@Service
@Transactional
public class AplicationUserService implements IAplicationUserService {
	@Autowired
	IAplicationUserDao aplicationUserDao;
	
	@Override
	public User findByUsername(String username) {
		//User aplicationUser = aplicationUserDao.findById((long) 1).get();
		User user = aplicationUserDao.findByUsername(username);
		return user;
	}

	@Override
	public void save(User user) {
		aplicationUserDao.save(user);
	}

	@Override
	public boolean existsByUsername(String username) {
		return aplicationUserDao.existsByUsername(username);
	}

}

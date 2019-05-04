package com.martin.motomandado.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martin.motomandado.model.User;

public interface IAplicationUserDao extends JpaRepository<User, Long> {
	User findByUsername(String username); //El JPARepository automaticamente ya hace la implementacion contra el campo "Username"
	boolean existsByUsername(String username);
}

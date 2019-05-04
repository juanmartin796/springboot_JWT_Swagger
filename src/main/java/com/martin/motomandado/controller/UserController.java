package com.martin.motomandado.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.martin.motomandado.dao.IAplicationUserDao;
import com.martin.motomandado.model.ApiResponse;
import com.martin.motomandado.model.User;
import com.martin.motomandado.services.IAplicationUserService;
import com.martin.motomandado.utils.RutasServicios;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	IAplicationUserService aplicationUserService;
	@Autowired
	IAplicationUserDao aplicationUserDao;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("{id}")
	public Optional<User> getUser(@PathVariable int id) {
		return aplicationUserDao.findById((long) id);
	}
	

	@RequestMapping(value ="helloworld", method = RequestMethod.GET)
	//@GetMapping("helloworld")
	public List<User> getUsers() {
		return aplicationUserDao.findAll();
	}
	
	
	@PostMapping("/sign-up")
    public ResponseEntity<ApiResponse> signUp(@RequestBody User user) {
		if (!aplicationUserService.existsByUsername(user.getUsername())) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    		aplicationUserService.save(user);
    		System.out.println(user);
    		
    		URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/users/{userId}")
                    .buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).body(new ApiResponse(true, "Usuario registrado correctamente"));
    		
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Usuario ya existente"), HttpStatus.BAD_REQUEST);
    }
}

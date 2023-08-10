package com.example.pedibus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pedibus.model.User;
import com.example.pedibus.repository.UserRepository;
import com.example.pedibus.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User addUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> addUsers(List<User> users) {
		
		return userRepository.saveAll(users);
	}

}

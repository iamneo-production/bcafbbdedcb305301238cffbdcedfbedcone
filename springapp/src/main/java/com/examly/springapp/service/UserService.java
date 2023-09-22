package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;


@Service
public class UserService{

	@Autowired
	private UserRepo userRepo;

	public boolean saveUser(User user) {
		return userRepo.save(user) != null ? true : false;
	}

	public boolean updateUser(User user, int userId) {
			return userRepo.save(user) != null ? true : false;
	
	}

	public boolean deleteUser(int id) {
		if (userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}

	public List<User> getAllUser() {

		List<User> userList = userRepo.findAll();
		return userList;
	}

	public User getUserById(int id) {

		if (userRepo.existsById(id)) {
			User user = userRepo.findById(id).get();
			return user;
		}

		return null;
	}

}

package com.fse.projectmgr.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmgr.dao.UserDao;
import com.fse.projectmgr.model.User;
import com.fse.projectmgr.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> fetchAllUsers() {
		
		List<User> usersList = userDao.fetchAllUsers();
		
		return usersList;
	}

	@Override
	public User fetchUserById(Integer userId) {
		
		User user = userDao.fetchUserById(userId);
		
		return user;
		
	}

	@Override
	public User fetchUserByEmployeeId(String employeeId) {
		
		User user = userDao.fetchUserByEmployeeId(employeeId);
		
		return user;
	}

	@Transactional
	@Override
	public void saveUser(User user) {
		
		userDao.saveUser(user);		
	}
	
	
	@Transactional
	@Override
	public void deleteUser(Integer userId) {
		
		User userToDelete = userDao.fetchUserById(userId);
		
		userDao.deleteUser(userToDelete);		
	}
	

}

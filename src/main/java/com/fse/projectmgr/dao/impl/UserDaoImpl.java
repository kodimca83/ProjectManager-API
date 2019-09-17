package com.fse.projectmgr.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fse.projectmgr.dao.UserDao;
import com.fse.projectmgr.model.User;
import com.fse.projectmgr.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User fetchUserById(Integer userId) {
		User user = null;
		try {
			user = userRepository.findById(userId).get();
		} catch (NoSuchElementException nseExce) {}

		return user;
	}

	@Override
	public User fetchUserByEmployeeId(String employeeId) {
		return userRepository.findUserByEmployeeId(employeeId);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public void updateUserForProject(Integer projectId) {
		userRepository.updateUserForProject(projectId);
	}

	@Override
	public void updateUserForTask(Integer taskId) {
		userRepository.updateUserForTask(taskId);
	}
}

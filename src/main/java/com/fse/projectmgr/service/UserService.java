package com.fse.projectmgr.service;

import java.util.List;

import com.fse.projectmgr.model.User;

public interface UserService {

	List<User> fetchAllUsers();

	User fetchUserById(Integer userId);

	User fetchUserByEmployeeId(String employeeId);

	void saveUser(User user);

	void deleteUser(Integer userId);
}

package com.fse.projectmgr.dao;

import java.util.List;

import com.fse.projectmgr.model.User;

public interface UserDao {

	List<User> fetchAllUsers();

	User fetchUserById(Integer userId);

	User fetchUserByEmployeeId(String employeeId);

	void saveUser(User user);

	void updateUserForProject(Integer projectId);

	void updateUserForTask(Integer taskId);

	void deleteUser(User user);
}

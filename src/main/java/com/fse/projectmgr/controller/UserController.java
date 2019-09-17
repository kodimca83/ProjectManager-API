package com.fse.projectmgr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projectmgr.model.User;
import com.fse.projectmgr.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * This method is to fetch all the Users
	 * 
	 * @return list of users
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userService.fetchAllUsers();
	}

	/**
	 * This method is to fetch an User for the given User Id
	 * 
	 * @param userId
	 * @return User
	 */
	@RequestMapping(value = "/fetch/{userId}", method = RequestMethod.GET)
	public User fetchUserById(@PathVariable Integer userId) {
		return userService.fetchUserById(userId);
	}

	/**
	 * This method is to fetch an User by an Employee Id
	 * 
	 * @param employeeId
	 * @return User
	 */
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
	public User fetchUserById(@PathVariable String employeeId) {
		return userService.fetchUserByEmployeeId(employeeId);
	}

	/**
	 * This method is to save the created User
	 * 
	 * @param User
	 */
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public void saveUser(@RequestBody User user) {
		userService.saveUser(user);
	}

	/**
	 * This method is to update an User
	 * 
	 * @param User
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		userService.saveUser(user);
	}

	/**
	 * This method is to delete an User
	 * 
	 * @return User
	 */
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
	}
}

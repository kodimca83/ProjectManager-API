package com.fse.projectmgr.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fse.projectmgr.model.ParentTask;
import com.fse.projectmgr.model.Task;
import com.fse.projectmgr.service.TaskService;

@RestController
@CrossOrigin
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	/**
	 * This method is used to fetch all the Parent Tasks
	 * 
	 * @return list of Parent Tasks
	 */
	@RequestMapping(value = "/allParentTasks", method = RequestMethod.GET)
	public List<ParentTask> getAllParentTasks() {
		return taskService.fetchAllParentTasks();
	}
	
	/**
	 * This method is used to fetch all the Tasks
	 * 
	 * @return list of projects
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Task> getAllTasks() {
		return taskService.fetchAllTasks();
	}

	/**
	 * This method returns the Task for the given Task Id
	 * 
	 * @param taskId
	 * @return Task
	 */
	@RequestMapping(value = "/fetch/{taskId}", method = RequestMethod.GET)
	public Task fetchUserById(@PathVariable Integer taskId) {
		return taskService.fetchTaskById(taskId);
	}

	/**
	 * This method is to save the created Task
	 * 
	 * @param task
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void saveTask(@RequestBody Task task) throws ParseException {
		taskService.saveTask(task, false);
	}

	/**
	 * This method is to update a Task
	 * 
	 * @param task
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void updateTask(@RequestBody Task task) throws ParseException {
		taskService.updateTask(task);
	}

	/**
	 * This method is to end a Task
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping(value = "/endtask/{taskId}", method = RequestMethod.PUT)
	public void endtask(@PathVariable Integer taskId) {
		taskService.endTask(taskId);
	}

}

package com.fse.projectmgr.service;

import java.text.ParseException;
import java.util.List;

import com.fse.projectmgr.model.ParentTask;
import com.fse.projectmgr.model.Task;

public interface TaskService {
	
	List<ParentTask> fetchAllParentTasks();

	List<Task> fetchAllTasks();

	Task fetchTaskById(Integer taskId);

	void updateTask(Task task) throws ParseException;

	void saveTask(Task task, boolean isUpdate) throws ParseException;

	void endTask(Integer taskId);

}

package com.fse.projectmgr.dao;

import java.util.List;

import com.fse.projectmgr.model.ParentTask;
import com.fse.projectmgr.model.Task;

public interface TaskDao {

	List<ParentTask> fetchAllParentTasks();

	List<Task> fetchAllTasks();

	Task fetchTaskById(Integer taskId);

	void saveTask(Task task);

	Integer fetchParentTask(String parenTaskName);

	void saveParentTask(ParentTask parentTask);

	void endTask(Integer taskId);

}

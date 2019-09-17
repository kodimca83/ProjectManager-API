package com.fse.projectmgr.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fse.projectmgr.dao.TaskDao;
import com.fse.projectmgr.model.ParentTask;
import com.fse.projectmgr.model.Task;
import com.fse.projectmgr.repository.ParentTaskRepository;
import com.fse.projectmgr.repository.TaskRepository;

@Repository
public class TaskDaoImpl implements TaskDao {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ParentTaskRepository parentTaskRepository;

	@Override
	public List<ParentTask> fetchAllParentTasks() {

		return parentTaskRepository.findAll();
	}

	@Override
	public List<Task> fetchAllTasks() {

		return taskRepository.findAll();
	}

	@Override
	public Task fetchTaskById(Integer taskId) {

		Task task = null;

		try {
			task = taskRepository.findById(taskId).get();
		} catch (NoSuchElementException nseExce) {

		}

		return task;
	}

	@Override
	public void saveTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public Integer fetchParentTask(String parenTaskName) {
		return taskRepository.findParentTask(parenTaskName);
	}

	@Override
	public void saveParentTask(ParentTask parentTask) {
		parentTaskRepository.save(parentTask);

	}

	@Override
	public void endTask(Integer taskId) {
		taskRepository.endTask(taskId);
	}

}

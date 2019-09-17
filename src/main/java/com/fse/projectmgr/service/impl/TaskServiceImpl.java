package com.fse.projectmgr.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fse.projectmgr.dao.ProjectDao;
import com.fse.projectmgr.dao.TaskDao;
import com.fse.projectmgr.dao.UserDao;
import com.fse.projectmgr.model.ParentTask;
import com.fse.projectmgr.model.Project;
import com.fse.projectmgr.model.Task;
import com.fse.projectmgr.model.User;
import com.fse.projectmgr.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private ProjectDao projectDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private TaskDao taskDao;

	final String DATE_PATTERN = "yyyy/MM/dd";

	@Override
	public List<ParentTask> fetchAllParentTasks() {
		return taskDao.fetchAllParentTasks();
	}

	@Override
	public List<Task> fetchAllTasks() {
		List<Task> tasksList = taskDao.fetchAllTasks();

		tasksList.forEach(task -> {
			convertStartAndEndDatesToString(task);
			setUserAndProjectDetails(task);
		});

		return tasksList;
	}

	@Override
	public Task fetchTaskById(Integer taskId) {
		Task task = null;

		task = taskDao.fetchTaskById(taskId);

		if (task != null) {
			convertStartAndEndDatesToString(task);
			setUserAndProjectDetails(task);
		}

		return task;
	}

	@Override
	public void updateTask(Task task) throws ParseException {
		if (task != null) {
			saveTask(task, true);
		}

	}

	@Transactional
	@Override
	public void endTask(Integer taskId) {
		taskDao.endTask(taskId);

		udpateUserForTask(taskId);
	}

	@Transactional
	@Override
	public void saveTask(Task task, boolean isUpdate) throws ParseException {
		if (task != null) {

			if (null != task.getParentTaskName()) {
				saveParentTask(task);
			}

			User user = setUserAndProjecDetails(task);

			SimpleDateFormat dateFrmt = new SimpleDateFormat(DATE_PATTERN);

			if (null != task.getStartDateStr() && !"".equalsIgnoreCase(task.getStartDateStr()))
				task.setStartDate(dateFrmt.parse(task.getStartDateStr()));

			if (null != task.getEndDateStr() && !"".equalsIgnoreCase(task.getEndDateStr()))
				task.setEndDate(dateFrmt.parse(task.getEndDateStr()));

			task.setUser(null);
			taskDao.saveTask(task);

			if (isUpdate) {
				udpateUserForTask(task.getTaskId());
			}

			if (user != null) {
				user.setTask(task);
				userDao.saveUser(user);
			}
		}
	}

	protected void setUserAndProjectDetails(Task task) {
		if (null != task.getUser()) {
			task.setUserString(task.getUser().getEmployeeId() + " - " + task.getUser().getFirstName() + " "
					+ task.getUser().getLastName());
		}

		if (null != task.getProject()) {
			task.setProjectName(task.getProject().getProject());
		} else {
			task.setProjectName("");
		}

		if (null != task.getParentTask()) {
			task.setParentTaskName(task.getParentTask().getParentTaskName());
		} else {
			task.setParentTaskName("No Parent Task");
		}
	}

	protected void saveParentTask(Task task) {
		ParentTask parentTask = new ParentTask();

		Integer parentId = taskDao.fetchParentTask(task.getParentTaskName());

		if (null != parentId) {
			parentTask.setParentId(parentId);
		} else {
			parentTask.setParentTaskName(task.getParentTaskName());
			taskDao.saveParentTask(parentTask);
		}

		task.setParentTask(parentTask);

	}

	protected void convertStartAndEndDatesToString(Task task) {
		SimpleDateFormat dateFrmt = new SimpleDateFormat(DATE_PATTERN);

		Date today = Calendar.getInstance().getTime();

		String startDate = "";
		String endDate = "";

		if (null != task.getStartDate()) {
			startDate = dateFrmt.format(task.getStartDate());
		}

		if (null != task.getEndDate())
			endDate = dateFrmt.format(task.getEndDate());

		task.setStartDateStr(startDate);
		task.setEndDateStr(endDate);

		if (null == task.getEndDate() || task.getEndDate().after(today)) {
			task.setCompleted("N");
		} else {
			task.setCompleted("Y");
		}
	}

	protected User setUserAndProjecDetails(Task task) {
		User assignedUser = null;
		Project assignedProject = null;

		if (null != task.getProjectName()) {
			assignedProject = projectDao.fetchProjectByProjectName(task.getProjectName().trim());
			task.setProject(assignedProject);
		}

		if (null != task.getUserString()) {

			String employeeId = task.getUserString().substring(0, task.getUserString().indexOf("-")).trim();

			assignedUser = userDao.fetchUserByEmployeeId(employeeId);

			task.setUser(assignedUser);
		}

		return assignedUser;

	}

	protected void udpateUserForTask(Integer taskId) {
		userDao.updateUserForTask(taskId);
	}
}

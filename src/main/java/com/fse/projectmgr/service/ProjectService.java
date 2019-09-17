package com.fse.projectmgr.service;

import java.text.ParseException;
import java.util.List;

import com.fse.projectmgr.model.Project;

public interface ProjectService {

	List<Project> fetchAllProjects();

	Project fetchProjectById(Integer projectId);

	Project fetchProjectByProjectName(String projectName);

	void suspendProject(Integer projectId);

	void updateProject(Project project) throws ParseException;

	void saveProject(Project project, boolean isUpdate) throws ParseException;
}

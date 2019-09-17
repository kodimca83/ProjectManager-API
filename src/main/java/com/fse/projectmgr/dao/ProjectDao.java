package com.fse.projectmgr.dao;

import java.util.List;

import com.fse.projectmgr.model.Project;

public interface ProjectDao {

	List<Project> fetchAllProjects();

	Project fetchProjectById(Integer projectId);

	Project fetchProjectByProjectName(String projectName);

	void suspendProject(Integer projectId);

	void saveProject(Project project);
}

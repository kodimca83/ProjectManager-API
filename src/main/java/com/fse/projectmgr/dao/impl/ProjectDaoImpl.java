package com.fse.projectmgr.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fse.projectmgr.dao.ProjectDao;
import com.fse.projectmgr.model.Project;
import com.fse.projectmgr.repository.ProjectRepository;

@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> fetchAllProjects() {

		return projectRepository.findAll();
	}

	@Override
	public Project fetchProjectById(Integer projectId) {

		Project project = null;

		try {

			project = projectRepository.findById(projectId).get();

		} catch (NoSuchElementException nseExce) {

		}

		return project;
	}

	@Override
	public Project fetchProjectByProjectName(String projectName) {

		return projectRepository.findProjectByName(projectName);
	}

	@Override
	public void saveProject(Project project) {

		projectRepository.save(project);
	}

	@Override
	public void suspendProject(Integer projectId) {

		projectRepository.suspendProject(projectId);

	}

}

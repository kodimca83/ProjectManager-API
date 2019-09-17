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

import com.fse.projectmgr.model.Project;
import com.fse.projectmgr.service.ProjectService;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	/**
	 * This method is used to fetch details of all the projects
	 * 
	 * @return list of projects
	 */
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Project> getAllProjects() {
		return projectService.fetchAllProjects();
	}

	/**
	 * This method returns the project detail for provided Project Id
	 * 
	 * @param projectId
	 * @return Project
	 */
	@RequestMapping(value = "/fetch/{projectId}", method = RequestMethod.GET)
	public Project fetchProjectById(@PathVariable Integer projectId) {
		return projectService.fetchProjectById(projectId);
	}

	/**
	 * This method returns the project detail for provided Project Name
	 * 
	 * @param projectName
	 * @return Project
	 */
	@RequestMapping(value = "/{projectName}", method = RequestMethod.GET)
	public Project fetchProjectByName(@PathVariable String projectName) {
		return projectService.fetchProjectByProjectName(projectName);
	}

	/**
	 * This method is used to suspend/end the Project
	 * 
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/suspend", method = RequestMethod.PUT)
	public void suspendProject(@RequestBody Project project) {
		projectService.suspendProject(project.getProjectId());
	}

	/**
	 * This method is to save the created Project
	 * 
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/addproject", method = RequestMethod.POST)
	public void saveProject(@RequestBody Project project) throws ParseException {
		projectService.saveProject(project, false);
	}

	/**
	 * This method is to update the Project
	 * 
	 * @param project
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public void updateProject(@RequestBody Project project) throws ParseException {
		projectService.updateProject(project);
	}

}

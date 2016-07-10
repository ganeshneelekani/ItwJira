package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.model.ItwRoles;

public interface ItwProjectService {
	
	public void addItwProject(ItwProject itwProject);

	public List<ItwProject> listItwProjects(int langId);
	
	public ItwProject getItwProject(int id);
	
	public void deleteItwProject(ItwProject itwProject);
	
	public List<ItwProject> getItwProjectByShortName(String string);
}
package com.agileidc.itw.dao;


import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.model.ItwRoles;

public interface ItwProjectDao {
	
	public void addItwProject(ItwProject itwProject);

	public List<ItwProject> listItwProjects(int langId);
	
	public ItwProject getItwProject(int id);
	public List<ItwProject> getItwProjectByShortName(String shortName);
	public void deleteItwProject(ItwProject itwProject) throws HibernateException;
}
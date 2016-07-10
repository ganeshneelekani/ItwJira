package com.agileidc.itw.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwRoles;

public interface ItwRolesService {
	
	public void addItwRoles(ItwRoles itwRolesType);

	public List<ItwRoles> listItwRoles(int id);
	
	public ItwRoles getItwRoles(int id);
	
	public void deleteItwRoles(ItwRoles itwRolesType) throws HibernateException;

	public List<ItwRoles> getItwRoleByShortName(String string, int langId);
	public JRDataSource getDataSource();

}

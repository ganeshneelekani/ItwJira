package com.agileidc.itw.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwRoles;

public interface ItwRolesDao {
	
	public void addItwRoles(ItwRoles itwStatusType);

	public List<ItwRoles> listItwRoles(int id);

	public ItwRoles getItwRoles(int id);

	public void deleteItwRoles(ItwRoles itwStatusType)
			throws HibernateException;
	public JRDataSource getDataSource();

	public List<ItwRoles> getItwRoleByShortName(String shortName, int langId);

}

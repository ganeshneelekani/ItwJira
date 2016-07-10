package com.agileidc.itw.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwModule;

public interface ItwModuleDao {

	public void addItwModule(ItwModule itwModuleType);

	public List<ItwModule> listItwModules(int id);

	public ItwModule getItwModule(int id);

	public List<ItwModule> getItwModuleByShortName(String shortName);

	public void deleteItwModule(ItwModule itwModuleType)
			throws HibernateException;
	public JRDataSource getDataSource();
}

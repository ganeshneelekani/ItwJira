package com.agileidc.itw.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwModule;

public interface ItwModuleService {
	
	public void addItwModule(ItwModule itwModuleType);

	public List<ItwModule> listItwModule(int id);
	
	public ItwModule getItwModule(int id);
	
	public List<ItwModule> getItwModuleByShortName(String shortName);
	
	public void deleteItwModule(ItwModule itwModuleType) throws HibernateException;
	public JRDataSource getDataSource();
}

package com.agileidc.itw.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwReleaseProcess;


public interface ItwReleaseProcessService {
	
	public void addItwReleaseProcess(ItwReleaseProcess itwReleaseProcessType);

	public List<ItwReleaseProcess> listItwReleaseProcess(int id);
	
	public ItwReleaseProcess getItwReleaseProcess(int id);
	
	public List<ItwReleaseProcess> getItwReleaseProcessByShortName(String shortName);
	
	public void deleteItwReleaseProcess(ItwReleaseProcess itwReleaseProcessType) throws HibernateException;
	public JRDataSource getDataSource(Integer Id);
}

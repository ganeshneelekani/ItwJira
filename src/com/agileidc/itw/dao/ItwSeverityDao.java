package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwSeverity;
import com.agileidc.itw.model.ItwStagesTypes;
import com.agileidc.itw.model.ItwUser;

public interface ItwSeverityDao {

	public void addItwSeverity(ItwSeverity itwSeverity);

	public List<ItwSeverity> listItwSeveritys();

	public ItwSeverity getItwSeverity(int id);

	public void deleteItwSeverity(ItwSeverity itwSeverity)
			throws HibernateException;
	
	public List<ItwSeverity> listItwSeverityPreced(int langId,String predId);
	
	public List<ItwSeverity> listItwSeveritys(int langId);
	
	public List<ItwSeverity> getItwSeverityByShortName(String string);
}

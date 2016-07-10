package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwSeverity;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserIcon;

public interface ItwSeverityService {
	
	public void addItwSeverity(ItwSeverity itwSeverity)throws HibernateException;

	public List<ItwSeverity> listItwSeveritys();
	
	public List<ItwSeverity> listItwSeveritys(int langId);
	
	public ItwSeverity getItwSeverity(int id);
	
	public List<ItwSeverity> listItwSeverityPreced(int langId,String deflt);
	
	public void deleteItwSeverity(ItwSeverity itwSeverity)throws HibernateException;
	
	public List<ItwSeverity> getItwSeverityByShortName(String string);
}

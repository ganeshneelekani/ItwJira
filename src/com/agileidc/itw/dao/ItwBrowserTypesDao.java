package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwBrowserTypes;

public interface ItwBrowserTypesDao {
	
	public void addItwBrowserTypes(ItwBrowserTypes itwBrowserType);

	public List<ItwBrowserTypes> listItwBrowserTypess();
	
	public ItwBrowserTypes getItwBrowserTypes(int id);
	
	public void deleteItwBrowserTypes(ItwBrowserTypes itwBrowserType) throws HibernateException;
}

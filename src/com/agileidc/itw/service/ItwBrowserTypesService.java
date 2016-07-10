package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwBrowserTypes;

public interface ItwBrowserTypesService {
	
	public void addItwBrowserTypes(ItwBrowserTypes itwBrowserType);

	public List<ItwBrowserTypes> listItwBrowserTypes();
	
	public ItwBrowserTypes getItwBrowserTypes(int id);
	
	public void deleteItwBrowserTypes(ItwBrowserTypes itwBrowserType) throws HibernateException;
}

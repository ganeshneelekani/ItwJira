package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwLangTypes;

public interface ItwLangTypesService {
	
	public void addItwLangTypes(ItwLangTypes itwLangTypes);

	public List<ItwLangTypes> listItwLangTypes();
	
	public ItwLangTypes getItwLangTypes(int id);
	
	public int getItwLangTypesId(String langDesc);
	
	public void deleteItwLangTypes(ItwLangTypes itwLangTypes) throws HibernateException;
}

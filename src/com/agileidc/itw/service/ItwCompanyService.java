package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwUserIcon;

public interface ItwCompanyService {
	
	public void addItwCompany(ItwCompany itwCompany,ItwUserIcon itwUserIcon)throws HibernateException;

	public List<ItwCompany> listItwCompanys(int langId);
	
	public ItwCompany getItwCompany(int id);
	
	public void deleteItwCompany(ItwCompany itwCompany)throws HibernateException;
}

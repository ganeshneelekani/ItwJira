package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwCompany;

public interface ItwCompanyDao {

	public void addItwCompany(ItwCompany itwCompany);

	public List<ItwCompany> listItwCompanys(int langId);

	public ItwCompany getItwCompany(int id);

	public void deleteItwCompany(ItwCompany itwCompany)
			throws HibernateException;
}

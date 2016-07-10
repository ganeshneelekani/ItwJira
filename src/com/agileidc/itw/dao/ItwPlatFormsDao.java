package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwPlatForms;
import com.agileidc.itw.model.ItwPlatForms;

public interface ItwPlatFormsDao {
	
	public void addItwPlatForms(ItwPlatForms itwPlatForms);

	public List<ItwPlatForms> listItwPlatForms(int langId);

	public ItwPlatForms getItwPlatForms(int id);

	public void deleteItwPlatForms(ItwPlatForms itwPlatForms)
			throws HibernateException;
	
	public List<ItwPlatForms> listItwPlatFormsPreced(int langId,String predId);
	
}

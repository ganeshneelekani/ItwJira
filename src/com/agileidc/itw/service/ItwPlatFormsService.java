package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwPlatForms;
import com.agileidc.itw.model.ItwPlatForms;
import com.agileidc.itw.model.ItwUserIcon;

public interface ItwPlatFormsService {
	
	public void addItwPlatForms(ItwPlatForms itwPlatForms/*,ItwUserIcon itwUserIcon*/)throws HibernateException;

	public List<ItwPlatForms> listItwPlatForms(int langId);
	
	public ItwPlatForms getItwPlatForms(int id);
	
	public List<ItwPlatForms> listItwPlatFormsPreced(int langId,String deflt);
	
	public void deleteItwPlatForms(ItwPlatForms itwPlatForms)throws HibernateException;
}

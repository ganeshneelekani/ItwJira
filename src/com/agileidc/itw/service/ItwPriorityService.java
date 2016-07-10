package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwPriority;
import com.agileidc.itw.model.ItwUserIcon;

public interface ItwPriorityService {
	

	public void addItwPriority(ItwPriority itwPriority/*,ItwUserIcon itwUserIcon*/)throws HibernateException;

	public List<ItwPriority> listItwPriorities(int langId);
	
	public ItwPriority getItwPriority(int id);
	
	public List<ItwPriority> listItwPriorityPreced(int langId,String deflt);
	
	public void deleteItwPriority(ItwPriority itwPriority)throws HibernateException;
}

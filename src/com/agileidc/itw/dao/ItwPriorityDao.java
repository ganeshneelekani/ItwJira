package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwPriority;

public interface ItwPriorityDao {

	public void addItwPriority(ItwPriority itwPriority);

	public List<ItwPriority> listItwPrioritys(int langId);

	public ItwPriority getItwPriority(int id);

	public void deleteItwPriority(ItwPriority itwPriority)
			throws HibernateException;
	
	public List<ItwPriority> listItwPriorityPreced(int langId,String predId);
}

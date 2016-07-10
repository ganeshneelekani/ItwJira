package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwBrowserTypes;
import com.agileidc.itw.model.ItwRca;

public interface ItwRcaDao {
	
	public void addItwRca(ItwRca itwRca);

	public List<ItwRca> listItwRcas();
	public List<ItwRca> listItwRcasByTaskId(int taskId);
	
	public ItwRca getItwRca(int id);
	
	public void deleteItwRca(ItwRca itwRca) throws HibernateException;
}

package com.agileidc.itw.dao;


import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwUserGroup;

public interface ItwUserGroupDao {
	
	public void addItwUserGroup(ItwUserGroup itwUserGroup);

	public List<ItwUserGroup> listItwUserGroups(int langId);
	
	public ItwUserGroup getItwUserGroup(int id);
	public List<ItwUserGroup> getItwUserGroupByShortName(String shortName);
	public void deleteItwUserGroup(ItwUserGroup itwUserGroup) throws HibernateException;
}
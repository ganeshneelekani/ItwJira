package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwUserGroup;

public interface ItwUserGroupService {
	
	public void addItwUserGroup(ItwUserGroup itwUserGroup);

	public List<ItwUserGroup> listItwUserGroups(int langId);
	
	public ItwUserGroup getItwUserGroup(int id);
	
	public void deleteItwUserGroup(ItwUserGroup itwUserGroup);
	
	public List<ItwUserGroup> getItwUserGroupByShortName(String string);
}
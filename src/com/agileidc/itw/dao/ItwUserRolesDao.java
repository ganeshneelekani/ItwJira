package com.agileidc.itw.dao;

import java.util.List;

import com.agileidc.itw.model.ItwUserRoles;

public interface ItwUserRolesDao {
	
	public void addItwUserRoles(ItwUserRoles itwUserRoles);

	public List<ItwUserRoles> listItwUserRoles(int langId);
	
	public ItwUserRoles getItwUserRoles(int empid);
	
	public void deleteItwUserRoles(ItwUserRoles itwUserRoles);
}

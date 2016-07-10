package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwUserRoles;

public interface ItwUserRolesService {
	
	public void addItwUserRoles(ItwUserRoles itwUserRoles);

	public List<ItwUserRoles> listItwUserRoles(int langId);
	
	public ItwUserRoles getItwUserRoles(int id);
	
	public void deleteItwUserRoles(ItwUserRoles itwUserRoles);
}

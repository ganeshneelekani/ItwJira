package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwProjectModule;

public interface ItwProjectModuleService {
	
	public void addItwProjectModule(ItwProjectModule itwProjectModule);

	public List<ItwProjectModule> listItwProjectModule(int langId);
	
	public ItwProjectModule getItwProjectModule(int id);
	
	public void deleteItwProjectModule(ItwProjectModule itwProjectModule);
}

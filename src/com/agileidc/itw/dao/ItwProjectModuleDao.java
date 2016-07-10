package com.agileidc.itw.dao;

import java.util.List;

import com.agileidc.itw.model.ItwProjectModule;

public interface ItwProjectModuleDao {
	
	public void addItwProjectModule(ItwProjectModule itwProjectModule);

	public List<ItwProjectModule> listItwProjectModule(int langId);
	
	public ItwProjectModule getItwProjectModule(int empid);
	
	public void deleteItwProjectModule(ItwProjectModule itwProjectModule);
}

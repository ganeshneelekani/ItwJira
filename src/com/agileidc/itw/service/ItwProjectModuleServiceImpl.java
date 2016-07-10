package com.agileidc.itw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwProjectModuleDao;
import com.agileidc.itw.model.ItwProjectModule;
import com.agileidc.itw.model.ItwProject;

@Service("itwProjectModuleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwProjectModuleServiceImpl implements ItwProjectModuleService {

	@Autowired
	private ItwProjectModuleDao itwProjectModuleDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwProjectModule(ItwProjectModule itwProjectModule) {
		itwProjectModuleDao.addItwProjectModule(itwProjectModule);
	}
	
	public List<ItwProjectModule> listItwProjectModule(int langid) {
		return itwProjectModuleDao.listItwProjectModule(langid);
	}

	public ItwProjectModule getItwProjectModule(int empid) {
		return itwProjectModuleDao.getItwProjectModule(empid);
	}
	
	public void deleteItwProjectModule(ItwProjectModule itwProjectModule) {
		itwProjectModuleDao.deleteItwProjectModule(itwProjectModule);
	}
	


}

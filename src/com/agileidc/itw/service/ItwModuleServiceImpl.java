package com.agileidc.itw.service;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwModuleDao;
import com.agileidc.itw.dao.ItwModuleDao;
import com.agileidc.itw.model.ItwModule;
import com.agileidc.itw.model.ItwModule;

@Service("itwModuleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwModuleServiceImpl implements ItwModuleService {
	@Autowired
	private ItwModuleDao itwModuleTypeDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwModule(ItwModule itwModuleType) {
		itwModuleTypeDao.addItwModule(itwModuleType);
	}

	public List<ItwModule> listItwModule(int id) {
		return itwModuleTypeDao.listItwModules(id);

	}

	public ItwModule getItwModule(int empid) {
		return itwModuleTypeDao.getItwModule(empid);
	}

	public List<ItwModule> getItwModuleByShortName(String shortName){
		return itwModuleTypeDao.getItwModuleByShortName(shortName);
	}
	
	public void deleteItwModule(ItwModule itwModuleType)
			throws HibernateException {
		itwModuleTypeDao.deleteItwModule(itwModuleType);
	}

	@Override
	public JRDataSource getDataSource() {
		return itwModuleTypeDao.getDataSource();
	}


}

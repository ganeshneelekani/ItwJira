package com.agileidc.itw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwUserRolesDao;
import com.agileidc.itw.model.ItwUserRoles;

@Service("itwUserRolesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwUserRolesServiceImpl implements ItwUserRolesService {

	@Autowired
	private ItwUserRolesDao itwUserRolesDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwUserRoles(ItwUserRoles itwUserRoles) {
		itwUserRolesDao.addItwUserRoles(itwUserRoles);
	}
	
	public List<ItwUserRoles> listItwUserRoles(int langid) {
		return itwUserRolesDao.listItwUserRoles(langid);
	}

	public ItwUserRoles getItwUserRoles(int empid) {
		return itwUserRolesDao.getItwUserRoles(empid);
	}
	
	public void deleteItwUserRoles(ItwUserRoles itwUserRoles) {
		itwUserRolesDao.deleteItwUserRoles(itwUserRoles);
	}

}

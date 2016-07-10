package com.agileidc.itw.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwRolesDao;
import com.agileidc.itw.dao.ItwRolesDao;
import com.agileidc.itw.model.ItwRoles;
import com.agileidc.itw.model.ItwRoles;

@Service("itwRolesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwRolesServiceImpl implements ItwRolesService {
	@Autowired
	private ItwRolesDao itwRolesTypeDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwRoles(ItwRoles itwRolesType) {
		itwRolesTypeDao.addItwRoles(itwRolesType);
	}

	public List<ItwRoles> listItwRoles(int id) {
		return itwRolesTypeDao.listItwRoles(id);

	}

	public ItwRoles getItwRoles(int empid) {
		return itwRolesTypeDao.getItwRoles(empid);
	}

	public void deleteItwRoles(ItwRoles itwRolesType)
			throws HibernateException {
		itwRolesTypeDao.deleteItwRoles(itwRolesType);
	}

	@Override
	public List<ItwRoles> getItwRoleByShortName(String string, int langId) {
		return itwRolesTypeDao.getItwRoleByShortName(string,langId);
	}

	@Override
	public JRDataSource getDataSource() {
		return itwRolesTypeDao.getDataSource();
	}

}

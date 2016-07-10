package com.agileidc.itw.service;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwBrowserTypesDao;
import com.agileidc.itw.model.ItwBrowserTypes;

@Service("itwBrowserTypeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwBrowserTypesServiceImpl implements ItwBrowserTypesService {

	@Autowired
	private ItwBrowserTypesDao itwBrowserTypeDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwBrowserTypes(ItwBrowserTypes itwBrowserType) {
		itwBrowserTypeDao.addItwBrowserTypes(itwBrowserType);
	}

	public List<ItwBrowserTypes> listItwBrowserTypes() {
		return itwBrowserTypeDao.listItwBrowserTypess();

	}

	public ItwBrowserTypes getItwBrowserTypes(int empid) {
		return itwBrowserTypeDao.getItwBrowserTypes(empid);
	}

	public void deleteItwBrowserTypes(ItwBrowserTypes itwBrowserType)
			throws HibernateException {
		itwBrowserTypeDao.deleteItwBrowserTypes(itwBrowserType);
	}

}

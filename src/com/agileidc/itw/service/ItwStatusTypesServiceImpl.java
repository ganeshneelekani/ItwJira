package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwStatusTypesDao;
import com.agileidc.itw.model.ItwStatusTypes;

@Service("itwStatusTypesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwStatusTypesServiceImpl implements ItwStatusTypesService {

	@Autowired
	private ItwStatusTypesDao itwStatusTypesDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwStatusTypes(ItwStatusTypes itwStatusTypes) {
		itwStatusTypesDao.addItwStatusTypes(itwStatusTypes);
	}

	public List<ItwStatusTypes> listItwStatusTypes(int langId) {
		return itwStatusTypesDao.listItwStatusTypes(langId);
	}

	public List<ItwStatusTypes> listItwStatusPrecedTypes(int langId,
			Integer predId) {

		return itwStatusTypesDao.listItwStatusPrecedTypes(langId, predId);
	}

	public List<ItwStatusTypes> listItwStatusIDTypes(int langId,
			Integer statusId) {

		return itwStatusTypesDao.listItwStatusIDTypes(langId, statusId);
	}

	public List<ItwStatusTypes> listItwStatusDeftTypes(int langId, String deftId) {

		return itwStatusTypesDao.listItwStatusDeftTypes(langId, deftId);

	}

	public ItwStatusTypes getItwStatusTypes(int empid) {
		return itwStatusTypesDao.getItwStatusTypes(empid);
	}

	public void deleteItwStatusTypes(ItwStatusTypes itwStatusTypes)
			throws HibernateException {
		itwStatusTypesDao.deleteItwStatusTypes(itwStatusTypes);
	}

	@Override
	public List<ItwStatusTypes> getItwStatusTypesByShortName(String string) {
		return itwStatusTypesDao.getItwStatusTypesByShortName(string);
	}

	@Override
	public List<ItwStatusTypes> getListOfPreceding(int id) {
		return itwStatusTypesDao.getListOfPreceding(id);
	}
}

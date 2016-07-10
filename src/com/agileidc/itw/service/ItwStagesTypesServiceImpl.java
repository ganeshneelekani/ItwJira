package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwStagesTypesDao;
import com.agileidc.itw.model.ItwStagesTypes;


@Service("itwStagesTypesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwStagesTypesServiceImpl implements ItwStagesTypesService {

	@Autowired
	private ItwStagesTypesDao itwStagesTypesDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwStagesTypes(ItwStagesTypes itwStagesTypes) {
		itwStagesTypesDao.addItwStagesTypes(itwStagesTypes);
	}

	public List<ItwStagesTypes> listItwStagesTypes(int langId) {
		return itwStagesTypesDao.listItwStagesTypes(langId);
	}

	public List<ItwStagesTypes> listItwStagesPrecedTypes(int langId, Integer predId) {

		return itwStagesTypesDao.listItwStagesPrecedTypes(langId, predId);
	}

	public ItwStagesTypes getItwStagesTypes(int id) {
		return itwStagesTypesDao.getItwStagesTypes(id);
	}

	public void deleteItwStagesTypes(ItwStagesTypes itwStagesTypes)
			throws HibernateException {
		itwStagesTypesDao.deleteItwStagesTypes(itwStagesTypes);
	}

	public List<ItwStagesTypes> listItwStagesDefaultTypes(int langId, String deftId) {

		return itwStagesTypesDao.listItwStagesDeftTypes(langId, deftId);

	}
	
	@Override
	public List<ItwStagesTypes> getListOfPreceding(int id) {
		return itwStagesTypesDao.getListOfPreceding(id);
	}
	
	@Override
	public List<ItwStagesTypes> getItwStagesTypesByShortName(String string) {
		return itwStagesTypesDao.getItwStagesTypesByShortName(string);
	}

}

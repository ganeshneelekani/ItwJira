package com.agileidc.itw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwLangTypesDao;
import com.agileidc.itw.model.ItwLangTypes;

@Service("itwLangTypesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwLangTypesServiceImpl implements ItwLangTypesService {

	@Autowired
	private ItwLangTypesDao itwLangTypesDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwLangTypes(ItwLangTypes itwLangTypes) {
		itwLangTypesDao.addItwLangTypes(itwLangTypes);
	}
	
	public List<ItwLangTypes> listItwLangTypes() {
		return itwLangTypesDao.listItwLangTypes();

	}

	public ItwLangTypes getItwLangTypes(int empid) {
		return itwLangTypesDao.getItwLangTypes(empid);
	}
	
	public void deleteItwLangTypes(ItwLangTypes itwLangTypes) {
		itwLangTypesDao.deleteItwLangTypes(itwLangTypes);
	}

	public int getItwLangTypesId(String langDesc) {
		
		return itwLangTypesDao.getItwLangTypesId(langDesc);
	}

	

	

}

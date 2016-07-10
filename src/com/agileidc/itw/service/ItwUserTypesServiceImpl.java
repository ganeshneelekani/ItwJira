package com.agileidc.itw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwUserTypesDao;
import com.agileidc.itw.model.ItwUserTypes;

@Service("itwUserTypesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwUserTypesServiceImpl implements ItwUserTypesService {

	@Autowired
	private ItwUserTypesDao itwUserTypesDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwUserTypes(ItwUserTypes itwUserTypes) {
		itwUserTypesDao.addItwUserTypes(itwUserTypes);
	}
	
	public List<ItwUserTypes> listItwUserTypes() {
		return itwUserTypesDao.listItwUserTypes();
	}

	public ItwUserTypes getItwUserTypes(int empid) {
		return itwUserTypesDao.getItwUserTypes(empid);
	}
	
	public void deleteItwUserTypes(ItwUserTypes itwUserTypes) {
		itwUserTypesDao.deleteItwUserTypes(itwUserTypes);
	}

}

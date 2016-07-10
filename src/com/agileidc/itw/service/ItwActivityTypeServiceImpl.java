package com.agileidc.itw.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwActivityTypeDao;
import com.agileidc.itw.model.ItwActivityType;

@Service("itwActivityTypeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwActivityTypeServiceImpl implements ItwActivityTypeService {

	@Autowired
	private ItwActivityTypeDao itwActivityTypeDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwActivityType(ItwActivityType itwActivityType) {
		itwActivityTypeDao.addItwActivityType(itwActivityType);
	}
	
	public List<ItwActivityType> listItwActivityTypes() {
		return itwActivityTypeDao.listItwActivityTypes();
	}

	public ItwActivityType getItwActivityType(int id) {
		return itwActivityTypeDao.getItwActivityType(id);
	}
	
	public void deleteItwActivityType(ItwActivityType itwActivityType) {
		itwActivityTypeDao.deleteItwActivityType(itwActivityType);
	}

}

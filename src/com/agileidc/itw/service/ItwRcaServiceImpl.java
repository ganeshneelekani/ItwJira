package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwRcaDao;
import com.agileidc.itw.model.ItwRca;

@Service("itwRcaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwRcaServiceImpl implements ItwRcaService {

	@Autowired
	private ItwRcaDao itwRcaDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwRca(ItwRca itwRca) {
		
		System.out.println("==========Inside Serveice =======");
		itwRcaDao.addItwRca(itwRca);
	}

	public List<ItwRca> listItwRcas() {
		return itwRcaDao.listItwRcas();

	}
	
	public List<ItwRca> listItwRcasByTaskId(int taskId){
		
		
		return itwRcaDao.listItwRcasByTaskId(taskId);
		
	}

	public ItwRca getItwRca(int id) {
		return itwRcaDao.getItwRca(id);
	}

	public void deleteItwRca(ItwRca itwRca)
			throws HibernateException {
		itwRcaDao.deleteItwRca(itwRca);
	}

}

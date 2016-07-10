package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwClientDao;
import com.agileidc.itw.model.ItwClient;

@Service("itwClientService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwClientServiceImpl implements ItwClientService {
	@Autowired
	private ItwClientDao itwClientTypeDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwClient(ItwClient itwClientType) {
		itwClientTypeDao.addItwClient(itwClientType);
	}

	public List<ItwClient> listItwClient(int id) {
		return itwClientTypeDao.listItwClients(id);

	}

	public ItwClient getItwClient(int empid) {
		return itwClientTypeDao.getItwClient(empid);
	}

	public List<ItwClient> getItwClientByShortName(String shortName){
		return itwClientTypeDao.getItwClientByShortName(shortName);
	}
	
	public void deleteItwClient(ItwClient itwClientType)
			throws HibernateException {
		itwClientTypeDao.deleteItwClient(itwClientType);
	}


}

package com.agileidc.itw.service;


import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwCheckOutDataDao;
import com.agileidc.itw.model.ItwCheckOutData;

@Service("itwCheckOutDataService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwCheckOutDataServiceImpl implements ItwCheckOutDataService {

	@Autowired
	private ItwCheckOutDataDao itwCheckOutDataDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwCheckOutData(ItwCheckOutData itwCheckOutData) {
		itwCheckOutDataDao.addItwCheckOutData(itwCheckOutData);
	}

	public List<ItwCheckOutData> listItwCheckOutData() {
		return itwCheckOutDataDao.listItwCheckOutData();

	}
	
	
	
	public List<ItwCheckOutData> getItwCheckOutDataOnIssueId(int id) {
		return itwCheckOutDataDao.getItwCheckOutDataOnIssueId(id);
	}

	public ItwCheckOutData getItwCheckOutData(int id) {
		return itwCheckOutDataDao.getItwCheckOutData(id);
	}

	public void deleteItwCheckOutData(ItwCheckOutData itwCheckOutData)
			throws HibernateException {
		itwCheckOutDataDao.deleteItwCheckOutData(itwCheckOutData);
	}

}

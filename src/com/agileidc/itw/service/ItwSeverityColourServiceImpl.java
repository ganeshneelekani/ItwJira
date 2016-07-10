package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwSeverityColourDao;
import com.agileidc.itw.model.ItwSeverityColour;

@Service("itwSeverityColourService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwSeverityColourServiceImpl implements ItwSeverityColourService {
	@Autowired
	private ItwSeverityColourDao itwSeverityColourDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwSeverityColour(ItwSeverityColour itwSeverityColour) {
		itwSeverityColourDao.addItwSeverityColour(itwSeverityColour);
	}

	public List<ItwSeverityColour> listItwSeverityColours(int id) {
		return itwSeverityColourDao.listItwSeverityColours(id);

	}

	public ItwSeverityColour getItwSeverityColour(int id) {
		return itwSeverityColourDao.getItwSeverityColour(id);
	}

	
	
	public void deleteItwSeverityColour(ItwSeverityColour itwSeverityColour)
			throws HibernateException {
		itwSeverityColourDao.deleteItwSeverityColour(itwSeverityColour);
	}


}

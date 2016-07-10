package com.agileidc.itw.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwReleaseProcessDao;
import com.agileidc.itw.model.ItwReleaseProcess;

@Service("itwReleaseProcessService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwReleaseProcessServiceImpl implements ItwReleaseProcessService {
	@Autowired
	private ItwReleaseProcessDao itwReleaseProcessTypeDao;

	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwReleaseProcess(ItwReleaseProcess itwReleaseProcessType) {
		
		
		itwReleaseProcessTypeDao.addItwReleaseProcess(itwReleaseProcessType);
	
	}

	public List<ItwReleaseProcess> listItwReleaseProcess(int id) {
		return itwReleaseProcessTypeDao.listItwReleaseProcesss(id);

	}

	public ItwReleaseProcess getItwReleaseProcess(int empid) {
		return itwReleaseProcessTypeDao.getItwReleaseProcess(empid);
	}

	public List<ItwReleaseProcess> getItwReleaseProcessByShortName(String shortName){
		return itwReleaseProcessTypeDao.getItwReleaseProcessByShortName(shortName);
	}
	
	public void deleteItwReleaseProcess(ItwReleaseProcess itwReleaseProcessType)
			throws HibernateException {
		itwReleaseProcessTypeDao.deleteItwReleaseProcess(itwReleaseProcessType);
	}

	@Override
	public JRDataSource getDataSource(Integer id) {
		return itwReleaseProcessTypeDao.getDataSource(id);
	}
}

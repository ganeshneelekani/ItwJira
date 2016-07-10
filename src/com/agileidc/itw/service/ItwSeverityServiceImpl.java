package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwSeverityDao;
import com.agileidc.itw.dao.ItwUserIconDao;
import com.agileidc.itw.model.ItwSeverity;
import com.agileidc.itw.model.ItwStagesTypes;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserIcon;

@Service("itwSeverityService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwSeverityServiceImpl implements ItwSeverityService {

	@Autowired
	private ItwSeverityDao itwSeverityDao;
	@Autowired
	private ItwUserIconDao itwUserIconDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwSeverity(ItwSeverity itwSeverity)throws HibernateException  {
		
		
		
		itwSeverityDao.addItwSeverity(itwSeverity);
		
	}
	
	public List<ItwSeverity> listItwSeveritys() {
		return itwSeverityDao.listItwSeveritys();
	}
	
	public List<ItwSeverity> listItwSeveritys(int langId) {
		return itwSeverityDao.listItwSeveritys(langId);
	}

	public ItwSeverity getItwSeverity(int id) {
		
		return itwSeverityDao.getItwSeverity(id);
	}
	
	public void deleteItwSeverity(ItwSeverity itwSeverity) throws HibernateException{
		itwSeverityDao.deleteItwSeverity(itwSeverity);
	}
	
	
	public List<ItwSeverity>  listItwSeverityPreced(int langId, String deflt) {

		return itwSeverityDao. listItwSeverityPreced(langId, deflt);
	}
	
	@Override
	public List<ItwSeverity> getItwSeverityByShortName(String string) {
		return itwSeverityDao.getItwSeverityByShortName(string);
	}

}

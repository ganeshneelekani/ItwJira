package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwReleasesDao;
import com.agileidc.itw.model.ItwReleases;

@Service("itwReleasesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwReleasesServiceImpl implements ItwReleasesService {

	@Autowired
	private ItwReleasesDao itwReleasesDao;
	
	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwReleases(ItwReleases itwReleases) {
		itwReleasesDao.addItwReleases(itwReleases);
		
		
		
	}
	
	public List<ItwReleases> listItwReleases(int langId) {
		return itwReleasesDao.listItwReleases(langId);
	}
	
	public List<ItwReleases> listItwReleasesonlyYes(int langId) {
		return itwReleasesDao.listItwReleasesonlyYes(langId);
	}
	
	public List<ItwReleases> listItwReleasesPrecedTypes(int langId,int predId) {
		
		return itwReleasesDao.listItwReleasesPrecedTypes(langId,predId);
	}
	
	public List<ItwReleases> listItwReleasesDeftTypes(int langId,String deftId){
		
		return itwReleasesDao.listItwReleasesDeftTypes(langId,deftId);
		
	}

	public ItwReleases getItwReleases(int empid) {
		return itwReleasesDao.getItwReleases(empid);
	}
	
	public void deleteItwReleases(ItwReleases itwReleases) throws HibernateException{
		itwReleasesDao.deleteItwReleases(itwReleases);
	}

	
	public List<ItwReleases> getItwReleaseByShortName(String shortName){
		return itwReleasesDao.getItwReleaseByShortName(shortName);
	}
	
	

}

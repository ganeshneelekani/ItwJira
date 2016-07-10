package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwReleaseDocument;
import com.agileidc.itw.model.ItwReleases;

public interface ItwReleasesService {
	
	public void addItwReleases(ItwReleases itwReleases);

	public List<ItwReleases> listItwReleases(int langId);
	
	public List<ItwReleases> listItwReleasesonlyYes(int langId);
	
	public List<ItwReleases> listItwReleasesDeftTypes(int langId,String deftId);
	
	public List<ItwReleases> listItwReleasesPrecedTypes(int langId,int predId);
	
	public ItwReleases getItwReleases(int id);
	
	public void deleteItwReleases(ItwReleases itwReleases)throws HibernateException;
	
	public List<ItwReleases> getItwReleaseByShortName(String shortName);

}

package com.agileidc.itw.dao;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwReleaseDocument;
import com.agileidc.itw.model.ItwRoles;

public interface ItwReleaseDocumentDao {

	public void addItwReleaseDocument(ItwReleaseDocument itwReleaseDocument);

	public List<ItwReleaseDocument> listItwReleaseDocuments(int langId);

	public ItwReleaseDocument getItwReleaseDocument(int id);

	public List<ItwReleaseDocument> getItwReleaseDocumentByShortName(
			String shortName);

	public void deleteItwReleaseDocument(ItwReleaseDocument itwReleaseDocument)
			throws HibernateException;
	
	public JRDataSource getDataSource(Integer id);
	
}
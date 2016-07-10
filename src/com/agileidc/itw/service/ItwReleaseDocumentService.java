package com.agileidc.itw.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.agileidc.itw.model.ItwReleaseDocument;
import com.agileidc.itw.model.ItwRoles;

public interface ItwReleaseDocumentService {
	
	public void addItwReleaseDocument(ItwReleaseDocument itwReleaseDocument);

	public List<ItwReleaseDocument> listItwReleaseDocuments(int langId);
	
	public ItwReleaseDocument getItwReleaseDocument(int id);
	
	public void deleteItwReleaseDocument(ItwReleaseDocument itwReleaseDocument);
	
	public List<ItwReleaseDocument> getItwReleaseDocumentByShortName(String string);
	
	public JRDataSource getDataSource(Integer Id);


}
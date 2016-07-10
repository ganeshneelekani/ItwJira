package com.agileidc.itw.service;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwReleaseDocumentDao;
import com.agileidc.itw.model.ItwReleaseDocument;
import com.agileidc.itw.model.ItwRoles;

@Service("itwReleaseDocumentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwReleaseDocumentServiceImpl implements ItwReleaseDocumentService {

	@Autowired
	private ItwReleaseDocumentDao itwReleaseDocumentDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwReleaseDocument(ItwReleaseDocument itwReleaseDocument) {
		itwReleaseDocumentDao.addItwReleaseDocument(itwReleaseDocument);
	}

	public List<ItwReleaseDocument> listItwReleaseDocuments(int langId) {
		return itwReleaseDocumentDao.listItwReleaseDocuments(langId);
	}

	public ItwReleaseDocument getItwReleaseDocument(int id) {
		return itwReleaseDocumentDao.getItwReleaseDocument(id);
	}

	public void deleteItwReleaseDocument(ItwReleaseDocument itwReleaseDocument) {
		itwReleaseDocumentDao.deleteItwReleaseDocument(itwReleaseDocument);
	}

	@Override
	public List<ItwReleaseDocument> getItwReleaseDocumentByShortName(
			String string) {
		return itwReleaseDocumentDao.getItwReleaseDocumentByShortName(string);
	}
	
	@Override
	public JRDataSource getDataSource(Integer id) {
		return itwReleaseDocumentDao.getDataSource(id);
	}

}

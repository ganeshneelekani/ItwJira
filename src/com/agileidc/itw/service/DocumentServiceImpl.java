
package com.agileidc.itw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.DocumentDao;
import com.agileidc.itw.dao.ItwUserIconDao;
import com.agileidc.itw.model.Document;
import com.agileidc.itw.model.ItwUserIcon;


@Service("documentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private ItwUserIconDao itwUserIconDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addDocument(Document document) {
		
		
			documentDao.addDocument(document);
		
		
	}
	
	public List<Document> listDocuments(int docid) {
		return documentDao.listDocuments(docid);
	}

	public Document getDocument(int docId) {
		return documentDao.getDocument(docId);
	}
	
	public void deleteDocument(int docId) {
		documentDao.deleteDocument(docId);
	}

	

}


package com.agileidc.itw.dao;

import java.util.List;

import com.agileidc.itw.model.Document;


public interface DocumentDao {
	
	public void addDocument(Document document);

	public List<Document> listDocuments(int id);
	
	public Document getDocument(int docId);
	
	public void deleteDocument(int docId);
}

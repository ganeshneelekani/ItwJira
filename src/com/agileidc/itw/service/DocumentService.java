package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.Document;
import com.agileidc.itw.model.ItwUserIcon;

public interface DocumentService {
	
	public void addDocument(Document document);

	public List<Document> listDocuments(int id);
	
	public Document getDocument(int docId);
	
	public void deleteDocument(int docId);
}
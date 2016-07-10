

package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.Document;


@Repository("documentDao")
public class DocumentDaoImpl implements DocumentDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addDocument(Document document) {
		sessionFactory.getCurrentSession().saveOrUpdate(document);
	}

	@SuppressWarnings("unchecked")
	public List<Document> listDocuments(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Document.class)
			    .add(Restrictions.eq("taskId", id));
	 List<Document> messageList = criteria.list();
	 return messageList;
	}

	public Document getDocument(int docId) {
		return (Document) sessionFactory.getCurrentSession().get(Document.class, docId);
	}

	public void deleteDocument(int docId) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM Document WHERE id = "+docId).executeUpdate();
	}

}

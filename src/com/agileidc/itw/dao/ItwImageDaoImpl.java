package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.Document;
import com.agileidc.itw.model.ItwImage;

@Repository("itwImageDao")
public class ItwImageDaoImpl implements ItwImageDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwImage(ItwImage itwImage) {

		sessionFactory.getCurrentSession().saveOrUpdate(itwImage);
			}
	
	@SuppressWarnings("unchecked")
	public List<ItwImage> listItwImage(int id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwImage.class)
			    .add(Restrictions.eq("taskId", id));
	 List<ItwImage> messageList = criteria.list();
	 return messageList;
	}

	public ItwImage getItwImage(int id) {
		return (ItwImage) sessionFactory.getCurrentSession().get(
				ItwImage.class, id);
	}

	public void deleteItwImage(int id) throws HibernateException {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwImage WHERE id = " + id)
				.executeUpdate();
	}

}

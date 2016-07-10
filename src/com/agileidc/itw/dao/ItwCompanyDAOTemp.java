package com.agileidc.itw.dao;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwUserIcon;

@Repository
public class ItwCompanyDAOTemp {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public ItwUserIcon getItwCompany(Integer id, OutputStream out) {
		Session session = sessionFactory.getCurrentSession();
		
		ItwUserIcon itwUserIcon = (ItwUserIcon) session.get(ItwUserIcon.class,
				id);
		try {
		
			IOUtils.copy(itwUserIcon.getIcon().getBinaryStream(), out);
		
		} catch (IOException e) {
			
			e.printStackTrace();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return itwUserIcon;
	}

}

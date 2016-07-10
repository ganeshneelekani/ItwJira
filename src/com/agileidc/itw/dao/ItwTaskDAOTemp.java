package com.agileidc.itw.dao;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

import com.agileidc.itw.model.ItwTaskBug;
import com.agileidc.itw.model.ItwUser;
import com.agileidc.itw.model.ItwUserIcon;

import org.apache.commons.io.IOUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ItwTaskDAOTemp {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public ItwTaskBug get(Integer id, OutputStream out) {
		Session session = sessionFactory.getCurrentSession();
		ItwTaskBug itwTaskBug = (ItwTaskBug) session.get(ItwTaskBug.class, id);
		try {
			
			IOUtils.copy(itwTaskBug.getIcon().getBinaryStream(), out);
			
		} catch (IOException e) {
			
			e.printStackTrace();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return itwTaskBug;
	}
	@Transactional
	public ItwUserIcon getItwUser(Integer id, OutputStream out) {
		Session session = sessionFactory.getCurrentSession();
		
		ItwUserIcon itwUserIcon = (ItwUserIcon) session.get(ItwUserIcon.class, id);
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

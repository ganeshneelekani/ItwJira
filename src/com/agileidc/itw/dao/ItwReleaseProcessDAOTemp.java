package com.agileidc.itw.dao;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;



import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.model.ItwReleaseProcess;

@Repository
public class ItwReleaseProcessDAOTemp {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public ItwReleaseProcess getItwReleaseProcess(Integer step,
			OutputStream out) {
		Session session = sessionFactory.getCurrentSession();
	 
		Query req=session
				.createQuery("from ItwReleaseProcess where step= " + step);
		ItwReleaseProcess newtempitwReleaseProcess=(ItwReleaseProcess) req.uniqueResult();
		try {

			IOUtils.copy(newtempitwReleaseProcess.getFile().getBinaryStream(), out);

		} catch (IOException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return newtempitwReleaseProcess;
	}

}

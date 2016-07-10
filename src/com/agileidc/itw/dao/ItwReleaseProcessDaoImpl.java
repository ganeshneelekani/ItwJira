package com.agileidc.itw.dao;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwReleaseDocument;
import com.agileidc.itw.model.ItwReleaseProcess;
import com.agileidc.itw.model.ItwReleaseProcess;

@Repository("itwReleaseProcessDao")
public class ItwReleaseProcessDaoImpl implements ItwReleaseProcessDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addItwReleaseProcess(ItwReleaseProcess itwReleaseProcessType) {
		

		sessionFactory.getCurrentSession().saveOrUpdate(itwReleaseProcessType);
	}

	public List<ItwReleaseProcess> getItwReleaseProcessByShortName(String shortName) {

		System.out.println("Inside ReleaseProcess Implimentation---------");
		
		List<ItwReleaseProcess> listItwReleaseProcesss = (List<ItwReleaseProcess>) sessionFactory
				.getCurrentSession().createCriteria(ItwReleaseProcess.class)
				.add(Restrictions.like("ReleaseProcessname", shortName)).list();

		return listItwReleaseProcesss;

	}

	@SuppressWarnings("unchecked")
	public List<ItwReleaseProcess> listItwReleaseProcesss(int langId) {

		ItwReleaseProcess value = new ItwReleaseProcess();

		List<ItwReleaseProcess> listItwReleaseProcesss = (List<ItwReleaseProcess>) sessionFactory
				.getCurrentSession().createCriteria(ItwReleaseProcess.class)
				.addOrder(Order.asc("step"))
				.add(Restrictions.like("langId", langId)).list();

		return listItwReleaseProcesss;

	}
	
	@SuppressWarnings("unchecked")
	public List<ItwReleaseProcess> getItwReleaseProcessPdf(int id) {
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwReleaseProcess.class)
				.add(Restrictions.eq("id", id));
		
		List<ItwReleaseProcess> messageList = criteria.list();
		
		
		return messageList;
	}

	public ItwReleaseProcess getItwReleaseProcess(int id) {

		ItwReleaseProcess itwReleaseProcess = (ItwReleaseProcess) sessionFactory.getCurrentSession()
				.get(ItwReleaseProcess.class, id);

	
		return itwReleaseProcess;

	}

	public void deleteItwReleaseProcess(ItwReleaseProcess itwReleaseProcessType)
			throws HibernateException {

		System.out.println(itwReleaseProcessType.getId() + "UUUU");
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwReleaseProcess WHERE id = "
								+ itwReleaseProcessType.getId()).executeUpdate();
	}
	@Override
	public JRDataSource getDataSource(Integer id) {
		
		
		
		List<ItwReleaseProcess> itwReleaseProcessList = listItwReleaseProcesss(1);
		
				
		JRDataSource ds = new JRBeanCollectionDataSource(itwReleaseProcessList); 
		return ds;
	}
	
}



package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwChartStatus;

@Repository("itwChartStatusDao")
public class ItwChartStatusDaoImpl implements ItwChartStatusDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addItwChartStatus(ItwChartStatus itwChartStatus) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwChartStatus);
	}

	@SuppressWarnings("unchecked")
	public List<ItwChartStatus> listItwChartStatus(int langid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ItwChartStatus.class)
			    .add(Restrictions.eq("langid", langid));
	 List<ItwChartStatus> messageList = criteria.list();
	 return messageList;
	}

	public ItwChartStatus getItwChartStatus(int id) {
		return (ItwChartStatus) sessionFactory.getCurrentSession().get(ItwChartStatus.class, id);
	}

	public void deleteItwChartStatus(int docId) {
		sessionFactory.getCurrentSession().createQuery("DELETE FROM ItwChartStatus WHERE id = "+docId).executeUpdate();
	}
	
	public List<ItwChartStatus> getItwChartStatusByShortName(String shortName) {

		@SuppressWarnings("unchecked")
		List<ItwChartStatus> listItwChartStatus = (List<ItwChartStatus>) sessionFactory
				.getCurrentSession().createCriteria(ItwChartStatus.class)
				.add(Restrictions.like("shortname", shortName)).list();

		
		return listItwChartStatus;

	}


}

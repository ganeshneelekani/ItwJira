package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwPriority;

@Repository("itwCompanyDao")
public class ItwCompanyDaoImpl implements ItwCompanyDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwCompany(ItwCompany itwCompany) {

		String SQL_QUERY = "select max(id)from ItwUserIcon ";
		Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		List list = query.list();
		System.out.println("Max  Id in table ItwUserIcon: " + list.get(0));
		Integer tempiconid = (Integer) list.get(0);
		itwCompany.setLogoId(tempiconid);

		sessionFactory.getCurrentSession().saveOrUpdate(itwCompany);
	}

	@SuppressWarnings("unchecked")
	public List<ItwCompany> listItwCompanys(int langId) {
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwCompany.class)
				.add(Restrictions.like("langId", langId))
				.addOrder(Order.desc("id"));
		List<ItwCompany> companyList = criteria.list();
		return companyList;
	}

	public ItwCompany getItwCompany(int id) {
		return (ItwCompany) sessionFactory.getCurrentSession().get(
				ItwCompany.class, id);
	}

	public void deleteItwCompany(ItwCompany itwCompany)
			throws HibernateException {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwCompany WHERE id = "
								+ itwCompany.getId()).executeUpdate();
	}

}

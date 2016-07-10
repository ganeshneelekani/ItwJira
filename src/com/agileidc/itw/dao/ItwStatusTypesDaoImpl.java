package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwReleases;
import com.agileidc.itw.model.ItwStatusTypes;

@Repository("itwStatusTypesDao")
public class ItwStatusTypesDaoImpl implements ItwStatusTypesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwStatusTypes(ItwStatusTypes itwStatusTypes) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwStatusTypes);
	}

	@SuppressWarnings("unchecked")
	public List<ItwStatusTypes> listItwStatusTypes(int langId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwStatusTypes.class)
				.add(Restrictions.eq("langId", langId))

				.addOrder(Order.desc("id"));
		List<ItwStatusTypes> StatusTypeList = criteria.list();
		return StatusTypeList;

	}

	@SuppressWarnings("unchecked")
	public List<ItwStatusTypes> listItwStatusPrecedTypes(int langId,
			Integer predId) {

		String predIdString = "%," + predId.toString() + ",%";

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwStatusTypes.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.like("precedingId", predIdString))
				.addOrder(Order.desc("id"));
		List<ItwStatusTypes> StatusTypeList = criteria.list();
		return StatusTypeList;

	}

	@SuppressWarnings("unchecked")
	public List<ItwStatusTypes> listItwStatusDeftTypes(int langId, String deftId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwStatusTypes.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.eq("defaultvalue", deftId))
				.addOrder(Order.desc("id"));
		List<ItwStatusTypes> StatusTypeList = criteria.list();
		return StatusTypeList;

	}

	public ItwStatusTypes getItwStatusTypes(int id) {
		return (ItwStatusTypes) sessionFactory.getCurrentSession().get(
				ItwStatusTypes.class, id);
	}

	public void deleteItwStatusTypes(ItwStatusTypes itwStatusTypes)
			throws HibernateException {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwStatusTypes WHERE id = "
								+ itwStatusTypes.getId()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<ItwStatusTypes> listItwStatusIDTypes(int langId,
			Integer statusId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwStatusTypes.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.like("statusType", statusId))
				.addOrder(Order.desc("id"));
		List<ItwStatusTypes> StatusTypeList = criteria.list();
		return StatusTypeList;

	}

	@Override
	public List<ItwStatusTypes> getItwStatusTypesByShortName(String shortname) {

		@SuppressWarnings("unchecked")
		List<ItwStatusTypes> listItwRoles1 = (List<ItwStatusTypes>) sessionFactory
				.getCurrentSession().createCriteria(ItwStatusTypes.class)
				.add(Restrictions.like("shortname", shortname)).list();

		return listItwRoles1;
	}

	@Override
	public List<ItwStatusTypes> getListOfPreceding(int id) {

		@SuppressWarnings("unchecked")
		List<ItwStatusTypes> listItwStagesTypes = (List<ItwStatusTypes>) sessionFactory
				.getCurrentSession().createCriteria(ItwStatusTypes.class)
				.add(Restrictions.like("precedingId", id)).list();

		return listItwStagesTypes;

	}

}

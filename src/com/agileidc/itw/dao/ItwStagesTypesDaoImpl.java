package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwStagesTypes;

@Repository("itwStagesTypesDao")
public class ItwStagesTypesDaoImpl implements ItwStagesTypesDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwStagesTypes(ItwStagesTypes itwStagesTypes) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwStagesTypes);
	}

	@SuppressWarnings("unchecked")
	public List<ItwStagesTypes> listItwStagesTypes(int langId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwStagesTypes.class)
				.add(Restrictions.eq("langId", langId))
				.addOrder(Order.desc("id"));
		List<ItwStagesTypes> StagesTypeList = criteria.list();
		return StagesTypeList;

	}

	@SuppressWarnings("unchecked")
	public List<ItwStagesTypes> listItwStagesPrecedTypes(int langId, Integer predId) {

		String predIdString = "%," + predId.toString() + ",%";
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwStagesTypes.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.like("precedingId", predIdString))
				.addOrder(Order.desc("id"));
		List<ItwStagesTypes> StagesTypeList = criteria.list();
		return StagesTypeList;

	}

	public ItwStagesTypes getItwStagesTypes(int id) {
		return (ItwStagesTypes) sessionFactory.getCurrentSession().get(
				ItwStagesTypes.class, id);
	}

	public void deleteItwStagesTypes(ItwStagesTypes itwStagesTypes)
			throws HibernateException {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwStagesTypes WHERE id = "
								+ itwStagesTypes.getId()).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<ItwStagesTypes> listItwStagesDeftTypes(int langId, String deftId) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwStagesTypes.class)
				.add(Restrictions.eq("langId", langId))
				.add(Restrictions.eq("defaultvalue", deftId))
				.addOrder(Order.desc("id"));
		List<ItwStagesTypes> StagesTypeList = criteria.list();
		return StagesTypeList;

	}

	@Override
	public List<ItwStagesTypes> getListOfPreceding(int id) {

		@SuppressWarnings("unchecked")
		List<ItwStagesTypes> listItwStagesTypes = (List<ItwStagesTypes>) sessionFactory
				.getCurrentSession().createCriteria(ItwStagesTypes.class)
				.add(Restrictions.like("precedingId", id)).list();

		return listItwStagesTypes;

	}

	@Override
	public List<ItwStagesTypes> getItwStagesTypesByShortName(String shortname) {

		@SuppressWarnings("unchecked")
		List<ItwStagesTypes> listItwRoles1 = (List<ItwStagesTypes>) sessionFactory
				.getCurrentSession().createCriteria(ItwStagesTypes.class)
				.add(Restrictions.like("shortname", shortname)).list();

		return listItwRoles1;
	}

}

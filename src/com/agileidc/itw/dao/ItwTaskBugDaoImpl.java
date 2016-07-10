package com.agileidc.itw.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwStatusTypes;
import com.agileidc.itw.model.ItwTaskBug;

@Repository("itwTaskBugDao")
public class ItwTaskBugDaoImpl implements ItwTaskBugDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ItwStatusTypesDao itwStatusTypesDao;

	public void addItwTaskBug(ItwTaskBug itwTaskBug) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwTaskBug);
	}

	public List<ItwTaskBug> getItwTaskBugReleaseId(int id) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.add(Restrictions.eq("releaseid", id));

		List<ItwTaskBug> itwTaskBug = criteria.list();
		return itwTaskBug;
	}

	@SuppressWarnings("unchecked")
	public List<ItwTaskBug> listItwTaskBugs() {

		Integer statusId = null;

		List<ItwStatusTypes> itwStatusTypes = itwStatusTypesDao
				.getItwStatusTypesByShortName("Issue Closed");
		Iterator<ItwStatusTypes> iterator = itwStatusTypes.iterator();
		while (iterator.hasNext()) {
			ItwStatusTypes itwStatusTypes2 = (ItwStatusTypes) iterator.next();
			statusId = itwStatusTypes2.getId();

		}

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.add(Restrictions.ne("statusid", statusId))
				.addOrder(Order.desc("id"));

		List<ItwTaskBug> taskList = criteria.list();
		return taskList;

	}

	@SuppressWarnings("unchecked")
	public List<ItwTaskBug> listOpenItwTaskBugs() {

		Integer statusId = null;
		List<ItwStatusTypes> itwStatusTypes = itwStatusTypesDao
				.getItwStatusTypesByShortName("Issue Closed");
		Iterator<ItwStatusTypes> iterator = itwStatusTypes.iterator();
		while (iterator.hasNext()) {
			ItwStatusTypes itwStatusTypes2 = (ItwStatusTypes) iterator.next();
			statusId = itwStatusTypes2.getId();

		}
		System.out.println(statusId);
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.add(Restrictions.eq("statusid", statusId))
				.addOrder(Order.desc("id"));
		List<ItwTaskBug> taskList = criteria.list();
		return taskList;

	}

	@SuppressWarnings("unchecked")
	public List<ItwTaskBug> listOpenItwTaskBugsForProject(int projectId) {

		Integer statusId = null;
		List<ItwStatusTypes> itwStatusTypes = itwStatusTypesDao
				.getItwStatusTypesByShortName("Issue Closed");
		Iterator<ItwStatusTypes> iterator = itwStatusTypes.iterator();
		while (iterator.hasNext()) {
			ItwStatusTypes itwStatusTypes2 = (ItwStatusTypes) iterator.next();
			statusId = itwStatusTypes2.getId();

		}
		System.out.println(statusId);
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.add(Restrictions.eq("statusid", statusId))
				.add(Restrictions.eq("projectid", projectId))
				.addOrder(Order.desc("id"));
		List<ItwTaskBug> taskList = criteria.list();
		return taskList;

	}

	public ItwTaskBug getItwTaskBug(int id) {
		return (ItwTaskBug) sessionFactory.getCurrentSession().get(
				ItwTaskBug.class, id);
	}

	public List<ItwTaskBug> listGetItwTaskBug(Integer SearchedValue,
			String SeleactedSearch, Integer projectId) {

		Integer statusId = null;

		List<ItwStatusTypes> itwStatusTypes = itwStatusTypesDao
				.getItwStatusTypesByShortName("Issue Closed");
		Iterator<ItwStatusTypes> iterator = itwStatusTypes.iterator();
		while (iterator.hasNext()) {
			ItwStatusTypes itwStatusTypes2 = (ItwStatusTypes) iterator.next();
			statusId = itwStatusTypes2.getId();

		}

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.add(Restrictions.eq(SeleactedSearch, SearchedValue))
				.add(Restrictions.ne("statusid", statusId))
				.add(Restrictions.eq("projectid", projectId));

		List<ItwTaskBug> taskList = criteria.list();
		return taskList;

	}

	public List<ItwTaskBug> allListGetItwTaskBug(Integer SearchedValue,
			String SeleactedSearch) {

		Integer statusId = null;

		List<ItwStatusTypes> itwStatusTypes = itwStatusTypesDao
				.getItwStatusTypesByShortName("Issue Closed");
		Iterator<ItwStatusTypes> iterator = itwStatusTypes.iterator();
		while (iterator.hasNext()) {
			ItwStatusTypes itwStatusTypes2 = (ItwStatusTypes) iterator.next();
			statusId = itwStatusTypes2.getId();

		}

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.add(Restrictions.ne("statusid", statusId))
				.add(Restrictions.eq(SeleactedSearch, SearchedValue));

		List<ItwTaskBug> taskList = criteria.list();
		return taskList;

	}

	public void deleteItwTaskBug(ItwTaskBug itwTaskBug) {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwTaskBug WHERE id = "
								+ itwTaskBug.getId()).executeUpdate();
	}

	public Integer getItwTaskBugCount(int id) {

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.eq("statusid", id));

		Integer count = (Integer) criteria.uniqueResult();

		System.out.println("Count value is" + count);
		return count;
	}

	public Integer getItwTaskBugForDate(String date) {

		Date today = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		String dateInString = date;

		System.out.println();

		Date minDate = null;
		try {
			minDate = sdf.parse(date);

			System.out.println("Min Date is ====" + minDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create date 18-04-2011 - 00h00
		// -> We take the 1st date and add it 1 day in millisecond thanks to a
		// useful and not so known class
		Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));
		System.out.println("Max Date is ====" + maxDate);

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.ge("createdtime", minDate))
				.add(Restrictions.le("createdtime", today));
		Integer count = (Integer) criteria.uniqueResult();

		System.out.println("Count value is" + count);
		return count;
	}

	public Integer getItwTaskClosedBugForDate(String date, int statusid) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		String dateInString = date;

		Date today = new Date();
		System.out.println();

		Date minDate = null;
		try {
			minDate = sdf.parse(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create date 18-04-2011 - 00h00
		// -> We take the 1st date and add it 1 day in millisecond thanks to a
		// useful and not so known class
		Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.ge("createdtime", minDate))
				.add(Restrictions.le("createdtime", today))
				.add(Restrictions.eq("statusid", statusid));
		Integer count = (Integer) criteria.uniqueResult();

		System.out.println("Count value is" + count);
		return count;
	}

	public Integer getItwTaskSeverityBugForDate(String date, String maxdate,
			int severityId) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");

		Date today = new Date();

		Date minDate = null;
		Date maxDate = null;

		try {
			minDate = sdf.parse(date);
			maxDate = sdf.parse(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create date 18-04-2011 - 00h00
		// -> We take the 1st date and add it 1 day in millisecond thanks to a
		// useful and not so known class

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.ge("createdtime", minDate))
				.add(Restrictions.le("createdtime", today))
				.add(Restrictions.eq("severityid", severityId));
		Integer count = (Integer) criteria.uniqueResult();

		return count;
	}

	public Integer getItwTaskSeverityClosedBugForDate(String date,
			int severityId, int statusId) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");

		Date today = new Date();
		System.out.println();

		Date minDate = null;
		try {
			minDate = sdf.parse(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Create date 18-04-2011 - 00h00
		// -> We take the 1st date and add it 1 day in millisecond thanks to a
		// useful and not so known class
		Date maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.ge("createdtime", minDate))
				.add(Restrictions.le("createdtime", today))
				.add(Restrictions.eq("severityid", severityId))
				.add(Restrictions.eq("statusid", statusId));
		Integer count = (Integer) criteria.uniqueResult();

		System.out.println("Count value is" + count);
		System.out.println("Min Date is ====" + minDate);
		return count;
	}

	public List<ItwTaskBug> getItwTaskClosedBugForSeverity(int severityId,
			int statusId, String lastdate) {

		SimpleDateFormat formatForSeverity = new SimpleDateFormat(
				"dd-MMM-yy HH:mm:ss aaa");

		Date today = new Date();

		Date minDate = null;

		try {
			minDate = formatForSeverity.parse(lastdate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.add(Restrictions.eq("severityid", severityId))
				.add(Restrictions.ge("createdtime", minDate))
				.add(Restrictions.le("createdtime", today))
				.add(Restrictions.eq("statusid", statusId));

		List<ItwTaskBug> taskList = criteria.list();
		return taskList;
	}

	public Integer getItwTaskBugSeverityCount(int severityId, String lastDate) {

		SimpleDateFormat formatForSeverity = new SimpleDateFormat(
				"dd-MMM-yy HH:mm:ss aaa");

		Date today = new Date();

		Date minDate = null;

		try {
			minDate = formatForSeverity.parse(lastDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.setProjection(Projections.rowCount())
				.add(Restrictions.ge("createdtime", minDate))
				.add(Restrictions.le("createdtime", today))
				.add(Restrictions.eq("severityid", severityId));

		Integer count = (Integer) criteria.uniqueResult();

		System.out.println("Count value is" + count);
		return count;
	}

	public int getItwTaskMaxId(int assigneeid) {

		String SQL_QUERY = "select max(id)from ItwTaskBug  where assigneeid='"
				+ assigneeid + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(SQL_QUERY);
		List list = query.list();
		System.out.println("Max  Id in table ItwUserIcon: " + list.get(0));
		Integer tempiconid = (Integer) list.get(0);

		return tempiconid;

	}

	public List<ItwTaskBug> listItwTaskBugsForProject(int projectcId) {

		Integer statusId = null;

		List<ItwStatusTypes> itwStatusTypes = itwStatusTypesDao
				.getItwStatusTypesByShortName("Issue Closed");
		Iterator<ItwStatusTypes> iterator = itwStatusTypes.iterator();
		while (iterator.hasNext()) {
			ItwStatusTypes itwStatusTypes2 = (ItwStatusTypes) iterator.next();
			statusId = itwStatusTypes2.getId();

		}

		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwTaskBug.class)
				.add(Restrictions.ne("statusid", statusId))
				.add(Restrictions.eq("projectid", projectcId));

		List<ItwTaskBug> itwTaskBug = criteria.list();
		return itwTaskBug;
	}

}

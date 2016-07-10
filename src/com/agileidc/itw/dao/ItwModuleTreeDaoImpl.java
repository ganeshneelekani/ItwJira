package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agileidc.itw.model.ItwModuleTree;

@Repository("itwModuleTreeDao")
public class ItwModuleTreeDaoImpl implements ItwModuleTreeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void addItwModuleTree(ItwModuleTree itwModuleTree) {
		sessionFactory.getCurrentSession().saveOrUpdate(itwModuleTree);
	}

	@SuppressWarnings("unchecked")
	public List<ItwModuleTree> listItwModuleTree() {

		return (List<ItwModuleTree>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						//"SELECT * FROM (SELECT * FROM itw_module_tree ORDER BY ID) CONNECT BY PRIOR ID = PARENTID START WITH ID IN (SELECT ID FROM itw_module_tree  WHERE PARENTID = \'0\'  )")
				"SELECT * FROM itw_module_tree ORDER BY ID")
						.addEntity(ItwModuleTree.class).list();

	}

	@SuppressWarnings("unchecked")
	public List<ItwModuleTree> listItwModuleTreeByModuleId(int moduleId, String rootId) {

		return (List<ItwModuleTree>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT * FROM (SELECT * FROM itw_module_tree where moduleId  = " + moduleId + " ORDER BY ID) CONNECT BY PRIOR ID = PARENTID START WITH ID IN (SELECT ID FROM itw_module_tree  WHERE PARENTID = \'" +rootId + "\'  )")
				.addEntity(ItwModuleTree.class).list();

	}
	
	@SuppressWarnings("unchecked")
	public List<ItwModuleTree> listItwModuleTreeByModuleIdAndNodeName(int moduleId) {

		return (List<ItwModuleTree>) sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT * FROM itw_module_tree where moduleId  = " + moduleId +  "and node_name=\'Root\'")
				.addEntity(ItwModuleTree.class).list();

	}
	
	public ItwModuleTree getItwModuleTree(String id) {
		return (ItwModuleTree) sessionFactory.getCurrentSession().get(
				ItwModuleTree.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<ItwModuleTree> getItwModuleTreeNodeName(String nodename) {

		
		System.out.println("NODE NAME "+nodename);
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(ItwModuleTree.class)
				.add(Restrictions.eq("nodeName", nodename));
				
		List<ItwModuleTree> itwModuleTree = criteria.list();

		return itwModuleTree;

	}
	public void deleteItwModuleTree(ItwModuleTree itwModuleTree)
			throws HibernateException {

		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM ItwModuleTree WHERE id = "
								+ itwModuleTree.getId()).executeUpdate();
	}

}

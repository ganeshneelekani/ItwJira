package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwModuleTree;

public interface ItwModuleTreeDao {
	
	public void addItwModuleTree(ItwModuleTree itwModuleTree);

	public List<ItwModuleTree> listItwModuleTree();
	public List<ItwModuleTree> listItwModuleTreeByModuleId(int moduleId, String rootId) ;
	public List<ItwModuleTree> listItwModuleTreeByModuleIdAndNodeName(int moduleId) ;
	
	public ItwModuleTree getItwModuleTree(String id);
	public List<ItwModuleTree> getItwModuleTreeNodeName(String nodename);

	
	public void deleteItwModuleTree(ItwModuleTree itwModuleTree) throws HibernateException;
}
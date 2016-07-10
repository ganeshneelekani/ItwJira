package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwCheckOutData;
import com.agileidc.itw.model.ItwModuleTree;

public interface ItwModuleTreeService {
	
	public void addItwModuleTree(ItwModuleTree itwModuleTree, ItwCheckOutData itwCheckOutData);

	public List<ItwModuleTree> listItwModuleTree();
	public List<ItwModuleTree> listItwModuleTreeByModuleId(int moduleId, String rootId) ;
	public List<ItwModuleTree> listItwModuleTreeByModuleIdAndNodeName(int moduleId) ;
	
	public List<ItwModuleTree> getItwModuleTreeNodeName(String nodename);
	
	public ItwModuleTree getItwModuleTree(String id);
	
	public void deleteItwModuleTree(ItwModuleTree itwModuleTree)throws HibernateException;
}

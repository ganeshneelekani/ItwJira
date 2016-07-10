package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwCheckOutDataDao;
import com.agileidc.itw.dao.ItwModuleTreeDao;
import com.agileidc.itw.model.ItwCheckOutData;
import com.agileidc.itw.model.ItwModuleTree;

@Service("itwModuleTreeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwModuleTreeServiceImpl implements ItwModuleTreeService {

	@Autowired
	private ItwModuleTreeDao itwModuleTreeDao;
	@Autowired
	private ItwCheckOutDataDao itwCheckOutDataDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwModuleTree(ItwModuleTree itwModuleTree, ItwCheckOutData itwCheckOutData) {
		itwModuleTreeDao.addItwModuleTree(itwModuleTree);
		itwCheckOutDataDao.addItwCheckOutData(itwCheckOutData);
	}

	public List<ItwModuleTree> listItwModuleTree() {
		return itwModuleTreeDao.listItwModuleTree();

	}
	public List<ItwModuleTree> listItwModuleTreeByModuleId(int moduleId, String rootId) {
		return itwModuleTreeDao.listItwModuleTreeByModuleId(moduleId, rootId);

	}

	public List<ItwModuleTree> listItwModuleTreeByModuleIdAndNodeName(int moduleId) {
		return itwModuleTreeDao.listItwModuleTreeByModuleIdAndNodeName( moduleId);

	}
	

	public ItwModuleTree getItwModuleTree(String id) {
		return itwModuleTreeDao.getItwModuleTree(id);
	}
	public List<ItwModuleTree> getItwModuleTreeNodeName(String nodename) {
		return itwModuleTreeDao.getItwModuleTreeNodeName(nodename);
	}
	public void deleteItwModuleTree(ItwModuleTree itwModuleTree)
			throws HibernateException {
		itwModuleTreeDao.deleteItwModuleTree(itwModuleTree);
	}

}

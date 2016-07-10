package com.agileidc.itw.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwUserGroupDao;
import com.agileidc.itw.model.ItwUserGroup;
import com.agileidc.itw.model.ItwRoles;

@Service("itwUserGroupService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwUserGroupServiceImpl implements ItwUserGroupService {

	@Autowired
	private ItwUserGroupDao itwUserGroupDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwUserGroup(ItwUserGroup itwUserGroup) {
		itwUserGroupDao.addItwUserGroup(itwUserGroup);
	}
	
	public List<ItwUserGroup> listItwUserGroups(int langId) {
		return itwUserGroupDao.listItwUserGroups(langId);
	}

	public ItwUserGroup getItwUserGroup(int id) {
		return itwUserGroupDao.getItwUserGroup(id);
	}
	
	public void deleteItwUserGroup(ItwUserGroup itwUserGroup) {
		itwUserGroupDao.deleteItwUserGroup(itwUserGroup);
	}
	@Override
	public List<ItwUserGroup> getItwUserGroupByShortName(String string) {
		return itwUserGroupDao.getItwUserGroupByShortName(string);
	}

}

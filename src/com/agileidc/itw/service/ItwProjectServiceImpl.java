package com.agileidc.itw.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwProjectDao;
import com.agileidc.itw.model.ItwProject;
import com.agileidc.itw.model.ItwRoles;

@Service("itwProjectService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwProjectServiceImpl implements ItwProjectService {

	@Autowired
	private ItwProjectDao itwProjectDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwProject(ItwProject itwProject) {
		itwProjectDao.addItwProject(itwProject);
	}
	
	public List<ItwProject> listItwProjects(int langId) {
		return itwProjectDao.listItwProjects(langId);
	}

	public ItwProject getItwProject(int id) {
		return itwProjectDao.getItwProject(id);
	}
	
	public void deleteItwProject(ItwProject itwProject) {
		itwProjectDao.deleteItwProject(itwProject);
	}
	@Override
	public List<ItwProject> getItwProjectByShortName(String string) {
		return itwProjectDao.getItwProjectByShortName(string);
	}

}

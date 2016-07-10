package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwPlatFormsDao;
import com.agileidc.itw.dao.ItwUserIconDao;
import com.agileidc.itw.model.ItwPlatForms;
import com.agileidc.itw.model.ItwUserIcon;

@Service("itwPlatFormsService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwPlatFormsServiceImpl implements ItwPlatFormsService {

	@Autowired
	private ItwPlatFormsDao itwPlatFormsDao;
	@Autowired
	private ItwUserIconDao itwUserIconDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwPlatForms(ItwPlatForms itwPlatForms/*,ItwUserIcon itwUserIcon*/)throws HibernateException  {
		
		
		/*if (itwUserIcon.getIcon() != null)
		{
			itwUserIconDao.addItwUserIcon(itwUserIcon);
			
			itwPlatFormsDao.addItwPlatForms(itwPlatForms);
		}else{*/
		
		itwPlatFormsDao.addItwPlatForms(itwPlatForms);
	//	}
	}
	
	public List<ItwPlatForms> listItwPlatForms(int langId) {
		return itwPlatFormsDao.listItwPlatForms(langId);
	}

	public ItwPlatForms getItwPlatForms(int id) {
		
		return itwPlatFormsDao.getItwPlatForms(id);
	}
	
	public void deleteItwPlatForms(ItwPlatForms itwPlatForms) throws HibernateException{
		itwPlatFormsDao.deleteItwPlatForms(itwPlatForms);
	}
	
	
	public List<ItwPlatForms>  listItwPlatFormsPreced(int langId, String deflt) {

		return itwPlatFormsDao. listItwPlatFormsPreced(langId, deflt);
	}


	

	

}

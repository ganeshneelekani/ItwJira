package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwPriorityDao;
import com.agileidc.itw.dao.ItwPriorityDao;
import com.agileidc.itw.dao.ItwUserIconDao;
import com.agileidc.itw.model.ItwPriority;
import com.agileidc.itw.model.ItwPriority;
import com.agileidc.itw.model.ItwUserIcon;

@Service("itwPriorityService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwPriorityServiceImpl implements ItwPriorityService {

	@Autowired
	private ItwPriorityDao itwPriorityDao;
	@Autowired
	private ItwUserIconDao itwUserIconDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwPriority(ItwPriority itwPriority/*,ItwUserIcon itwUserIcon*/)throws HibernateException  {
		
		
	/*	if (itwUserIcon.getIcon() != null)
		{
			itwUserIconDao.addItwUserIcon(itwUserIcon);
			
			itwPriorityDao.addItwPriority(itwPriority);
		}else{*/
		
		itwPriorityDao.addItwPriority(itwPriority);
		//}
	}
	
	public List<ItwPriority> listItwPriorities(int langId) {
		return itwPriorityDao.listItwPrioritys(langId);
	}

	public ItwPriority getItwPriority(int id) {
		
		return itwPriorityDao.getItwPriority(id);
	}
	
	public void deleteItwPriority(ItwPriority itwPriority) throws HibernateException{
		itwPriorityDao.deleteItwPriority(itwPriority);
	}
	
	
	public List<ItwPriority>  listItwPriorityPreced(int langId, String deflt) {

		return itwPriorityDao. listItwPriorityPreced(langId, deflt);
	}

}

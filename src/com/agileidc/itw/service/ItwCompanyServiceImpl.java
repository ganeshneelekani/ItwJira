package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwCompanyDao;
import com.agileidc.itw.dao.ItwUserIconDao;
import com.agileidc.itw.model.ItwCompany;
import com.agileidc.itw.model.ItwUserIcon;

@Service("itwCompanyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwCompanyServiceImpl implements ItwCompanyService {

	@Autowired
	private ItwCompanyDao itwCompanyDao;
	@Autowired
	private ItwUserIconDao itwUserIconDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwCompany(ItwCompany itwCompany,ItwUserIcon itwUserIcon)throws HibernateException  {
		
		System.out.println("first condition before check....");
		if (itwUserIcon.getIcon() != null)
		{
			System.out.println("second condition after in if part check....");
			itwUserIconDao.addItwUserIcon(itwUserIcon);
			
			itwCompanyDao.addItwCompany(itwCompany);
		}else{
			System.out.println("third condition after in else part check....");
		itwCompanyDao.addItwCompany(itwCompany);
		}
	}
	
	public List<ItwCompany> listItwCompanys(int langId) {
		return itwCompanyDao.listItwCompanys(langId);
	}

	public ItwCompany getItwCompany(int id) {
		System.out.println("22222222");
		return itwCompanyDao.getItwCompany(id);
	}
	
	public void deleteItwCompany(ItwCompany itwCompany) throws HibernateException{
		itwCompanyDao.deleteItwCompany(itwCompany);
	}

}

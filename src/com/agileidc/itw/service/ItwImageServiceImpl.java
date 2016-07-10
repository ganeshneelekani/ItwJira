package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwImageDao;
import com.agileidc.itw.model.ItwImage;

@Service("itwImageService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwImageServiceImpl implements ItwImageService {

	@Autowired
	private ItwImageDao itwImageDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwImage(ItwImage itwImage) throws HibernateException {
		itwImageDao.addItwImage(itwImage);
	}

	public List<ItwImage> listItwImage(int id) {
		return itwImageDao.listItwImage(id);
	}

	public ItwImage getItwImage(int id) {
		return itwImageDao.getItwImage(id);
	}

	public void deleteItwImage(int id) throws HibernateException {
		itwImageDao.deleteItwImage(id);
	}

}

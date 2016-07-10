package com.agileidc.itw.service;


import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwCheckOutData;

public interface ItwCheckOutDataService {
	
	public void addItwCheckOutData(ItwCheckOutData itwCheckOutData);

	public List<ItwCheckOutData> listItwCheckOutData();
	
	public ItwCheckOutData getItwCheckOutData(int id);
	
	public List<ItwCheckOutData> getItwCheckOutDataOnIssueId(int id);
	
	public void deleteItwCheckOutData(ItwCheckOutData itwCheckOutData) throws HibernateException;
}
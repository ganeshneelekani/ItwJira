package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwClient;

public interface ItwClientService {
	
	public void addItwClient(ItwClient itwClientType);

	public List<ItwClient> listItwClient(int id);
	
	public ItwClient getItwClient(int id);
	
	public List<ItwClient> getItwClientByShortName(String shortName);
	
	public void deleteItwClient(ItwClient itwClientType) throws HibernateException;

}

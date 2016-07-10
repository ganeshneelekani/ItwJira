package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwClient;

public interface ItwClientDao {

	public void addItwClient(ItwClient itwClientType);

	public List<ItwClient> listItwClients(int id);

	public ItwClient getItwClient(int id);

	public List<ItwClient> getItwClientByShortName(String shortName);

	public void deleteItwClient(ItwClient itwClientType)
			throws HibernateException;

}

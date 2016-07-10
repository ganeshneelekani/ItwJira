package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwClient;
import com.agileidc.itw.model.ItwSeverityColour;

public interface ItwSeverityColourDao {

	public void addItwSeverityColour(ItwSeverityColour itwSeverityColour);

	public List<ItwSeverityColour> listItwSeverityColours(int id);

	public ItwSeverityColour getItwSeverityColour(int id);

	
	public void deleteItwSeverityColour(ItwSeverityColour itwSeverityColour)
			throws HibernateException;

}

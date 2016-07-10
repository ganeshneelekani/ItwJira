package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwSeverityColour;

public interface ItwSeverityColourService {
	
	public void addItwSeverityColour(ItwSeverityColour itwSeverityColour);

	public List<ItwSeverityColour> listItwSeverityColours(int id);

	public ItwSeverityColour getItwSeverityColour(int id);

	
	public void deleteItwSeverityColour(ItwSeverityColour itwSeverityColour)
			throws HibernateException;

}

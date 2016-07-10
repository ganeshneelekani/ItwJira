package com.agileidc.itw.dao;


import java.util.List;

import com.agileidc.itw.model.ItwActivityType;

public interface ItwActivityTypeDao {
	
	public void addItwActivityType(ItwActivityType itwActivityType);

	public List<ItwActivityType> listItwActivityTypes();
	
	public ItwActivityType getItwActivityType(int id);
	
	public void deleteItwActivityType(ItwActivityType itwActivityType);
}

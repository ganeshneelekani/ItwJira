package com.agileidc.itw.dao;

import java.util.List;

import com.agileidc.itw.model.ItwUserTypes;

public interface ItwUserTypesDao {
	
	public void addItwUserTypes(ItwUserTypes itwReleases);

	public List<ItwUserTypes> listItwUserTypes();
	
	public ItwUserTypes getItwUserTypes(int id);
	
	public void deleteItwUserTypes(ItwUserTypes itwReleases);
}

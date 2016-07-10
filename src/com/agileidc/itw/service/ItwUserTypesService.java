package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwUserTypes;

public interface ItwUserTypesService {
	
	public void addItwUserTypes(ItwUserTypes itwReleases);

	public List<ItwUserTypes> listItwUserTypes();
	
	public ItwUserTypes getItwUserTypes(int id);
	
	public void deleteItwUserTypes(ItwUserTypes itwReleases);
}

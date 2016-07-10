package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwStagesTypes;
import com.agileidc.itw.model.ItwStatusTypes;

public interface ItwStagesTypesService {

	public void addItwStagesTypes(ItwStagesTypes itwReleases);

	public List<ItwStagesTypes> listItwStagesTypes(int langId);

	public List<ItwStagesTypes> listItwStagesPrecedTypes(int langId, Integer predId);

	public ItwStagesTypes getItwStagesTypes(int id);

	public List<ItwStagesTypes> listItwStagesDefaultTypes(int langId, String deftId);

	public void deleteItwStagesTypes(ItwStagesTypes itwReleases)
			throws HibernateException;

	public List<ItwStagesTypes> getListOfPreceding(int id);

	public List<ItwStagesTypes> getItwStagesTypesByShortName(String string);
}

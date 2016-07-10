package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwStagesTypes;

public interface ItwStagesTypesDao {

	public void addItwStagesTypes(ItwStagesTypes itwStages);

	public List<ItwStagesTypes> listItwStagesTypes(int langId);

	public List<ItwStagesTypes> listItwStagesPrecedTypes(int langId, Integer predId);

	public List<ItwStagesTypes> listItwStagesDeftTypes(int langId, String deftId);

	public ItwStagesTypes getItwStagesTypes(int id);

	public void deleteItwStagesTypes(ItwStagesTypes itwStages)
			throws HibernateException;

	public List<ItwStagesTypes> getListOfPreceding(int id);

	public List<ItwStagesTypes> getItwStagesTypesByShortName(String string);
}

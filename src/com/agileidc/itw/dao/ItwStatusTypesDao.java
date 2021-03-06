package com.agileidc.itw.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwStatusTypes;

public interface ItwStatusTypesDao {

	public void addItwStatusTypes(ItwStatusTypes itwReleases);

	public List<ItwStatusTypes> listItwStatusTypes(int langId);

	public List<ItwStatusTypes> listItwStatusPrecedTypes(int langId,
			Integer predId);

	public List<ItwStatusTypes> listItwStatusIDTypes(int langId,
			Integer statusId);

	public List<ItwStatusTypes> listItwStatusDeftTypes(int langId, String deftId);

	public ItwStatusTypes getItwStatusTypes(int id);

	public void deleteItwStatusTypes(ItwStatusTypes itwReleases)
			throws HibernateException;

	public List<ItwStatusTypes> getListOfPreceding(int id);

	public List<ItwStatusTypes> getItwStatusTypesByShortName(String string);
}

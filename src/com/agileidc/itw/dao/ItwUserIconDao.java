package com.agileidc.itw.dao;


import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwUserIcon;;

public interface ItwUserIconDao {
	
	public void addItwUserIcon(ItwUserIcon itwUserIcon) throws HibernateException;

	public List<ItwUserIcon> listItwUserIcons();
	
	public ItwUserIcon getItwUserIcon(int id);
	
	public void deleteItwUserIcon(ItwUserIcon itwUserIcon);
}
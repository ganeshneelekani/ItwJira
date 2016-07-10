package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwImage;

public interface ItwImageService {

	public void addItwImage(ItwImage itwImage) throws HibernateException;

	public List<ItwImage> listItwImage(int id);

	public ItwImage getItwImage(int id);

	public void deleteItwImage(int id) throws HibernateException;
}
package com.agileidc.itw.dao;

import java.util.List;

import com.agileidc.itw.model.ItwImage;

public interface ItwImageDao {

	public void addItwImage(ItwImage itwImage);

	public List<ItwImage> listItwImage(int id);

	public ItwImage getItwImage(int id);

	public void deleteItwImage(int id);
}

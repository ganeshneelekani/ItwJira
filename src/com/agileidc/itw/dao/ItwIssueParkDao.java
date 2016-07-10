package com.agileidc.itw.dao;


import java.util.List;

import com.agileidc.itw.model.ItwIssuePark;

public interface ItwIssueParkDao {
	
	public void addItwIssuePark(ItwIssuePark itwIssuePark);
	
	public List<ItwIssuePark> listItwIssueParks();
	
	public ItwIssuePark getItwIssuePark(int id);
	
	public void deleteItwIssuePark(ItwIssuePark itwIssuePark);
	
	public List<ItwIssuePark> listItwIssuePark(int taskId);
}

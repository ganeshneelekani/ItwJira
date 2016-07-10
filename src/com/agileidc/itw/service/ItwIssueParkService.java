package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwIssuePark;

public interface ItwIssueParkService {
	
	public void addItwIssuePark(ItwIssuePark itwIssuePark);
	
	public List<ItwIssuePark> listItwIssuePark(int taskId);

	public List<ItwIssuePark> listItwIssueParks();
	
	public ItwIssuePark getItwIssuePark(int id);
	
	public void deleteItwIssuePark(ItwIssuePark itwIssuePark);
}

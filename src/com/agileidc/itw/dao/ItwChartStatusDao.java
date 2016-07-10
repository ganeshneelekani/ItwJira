
package com.agileidc.itw.dao;

import java.util.List;

import com.agileidc.itw.model.Document;
import com.agileidc.itw.model.ItwChartStatus;


public interface ItwChartStatusDao {
	
	public void addItwChartStatus(ItwChartStatus itwChartStatus);

	public List<ItwChartStatus> listItwChartStatus(int langid);
	
	public ItwChartStatus getItwChartStatus(int id);
	
	public void deleteItwChartStatus(int id);

	public List<ItwChartStatus> getItwChartStatusByShortName(String shortName);
}

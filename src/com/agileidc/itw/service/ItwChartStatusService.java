package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwChartStatus;

public interface ItwChartStatusService {

	public void addItwChartStatus(ItwChartStatus itwChartStatus);

	public List<ItwChartStatus> listItwChartStatus(int langid);

	public ItwChartStatus getItwChartStatus(int id);

	public void deleteItwChartStatus(int id);

	public List<ItwChartStatus> getItwChartStatusByShortName(String shortName);
}
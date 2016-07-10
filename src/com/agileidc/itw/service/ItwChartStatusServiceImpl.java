
package com.agileidc.itw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwChartStatusDao;

import com.agileidc.itw.model.ItwChartStatus;


@Service("itwChartStatusService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwChartStatusServiceImpl implements ItwChartStatusService {

	@Autowired
	private ItwChartStatusDao itwChartStatusDao;
		
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwChartStatus(ItwChartStatus itwChartStatus) {
		
		
		itwChartStatusDao.addItwChartStatus(itwChartStatus);
		
		
	}
	
	public List<ItwChartStatus> listItwChartStatus(int langid) {
		return itwChartStatusDao.listItwChartStatus(langid);
	}

	public ItwChartStatus getItwChartStatus(int docId) {
		return itwChartStatusDao.getItwChartStatus(docId);
	}
	
	public void deleteItwChartStatus(int docId) {
		itwChartStatusDao.deleteItwChartStatus(docId);
	}

	public List<ItwChartStatus> getItwChartStatusByShortName(String shortName){
		return itwChartStatusDao.getItwChartStatusByShortName(shortName);
	}
	

}

package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.agileidc.itw.dao.ItwRcaFeedbackDao;
import com.agileidc.itw.model.ItwRcaFeedback;

@Service("itwRcaFeedbackService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ItwRcaFeedbackServiceImpl implements ItwRcaFeedbackService {

	@Autowired
	private ItwRcaFeedbackDao itwRcaFeedbackDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addItwRcaFeedback(ItwRcaFeedback itwRcaFeedback) {

		
		itwRcaFeedbackDao.addItwRcaFeedback(itwRcaFeedback);
	}

	public List<ItwRcaFeedback> listItwRcaFeedbacks() {
		return itwRcaFeedbackDao.listItwRcaFeedbacks();

	}

	public List<ItwRcaFeedback> listItwRcaFeedbacksByTaskId(int taskId) {

		return itwRcaFeedbackDao.listItwRcaFeedbacksByTaskId(taskId);

	}

	public ItwRcaFeedback getItwRcaFeedback(int id) {
		return itwRcaFeedbackDao.getItwRcaFeedback(id);
	}

	public void deleteItwRcaFeedback(ItwRcaFeedback itwRcaFeedback)
			throws HibernateException {
		itwRcaFeedbackDao.deleteItwRcaFeedback(itwRcaFeedback);
	}

}

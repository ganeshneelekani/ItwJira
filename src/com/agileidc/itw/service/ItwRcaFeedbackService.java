package com.agileidc.itw.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.agileidc.itw.model.ItwRcaFeedback;

public interface ItwRcaFeedbackService {

	public void addItwRcaFeedback(ItwRcaFeedback itwRcaFeedback);

	public List<ItwRcaFeedback> listItwRcaFeedbacks();

	public List<ItwRcaFeedback> listItwRcaFeedbacksByTaskId(int taskId);

	public ItwRcaFeedback getItwRcaFeedback(int id);

	public void deleteItwRcaFeedback(ItwRcaFeedback itwRcaFeedback)
			throws HibernateException;
}

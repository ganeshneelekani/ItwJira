package com.agileidc.itw.service;

import java.util.List;

import com.agileidc.itw.model.ItwMessage;

public interface ItwMessageService {
	/**
	* This is the method to be used to send the message
	* to a user by passing user's userid as receiverid, message and logged-in userid as senderid .
	*/
	public void sendMessageToThisUser(String receiverId, String message, String senderId, Integer issueId);
	/**
	* This is the method to be used to get the messages
	* of a logged user by passing user's userid as loggedUserid .
	*/
	public List<ItwMessage> getMyLatestMessages(String loggedUserId,  Integer issueId);
	public List<ItwMessage> getAllMessagesForId(String loggedUserId,
			Integer issueId);
	/**
	* This is the method to be used to get the previous messages
	* of a logged user by passing user's userid as loggedUserid .
	*/
	
	public void sendMessageToAllUsers(String senderSessionId, String message, String senderId,Integer issueId);
	
	public List<ItwMessage> getMyPrevMessages(String loggedUserId, String minVal);
	/**
	* This is the method to be used to get the next messages
	* of a logged user by passing user's userid as loggedUserid .
	*/
	public List<ItwMessage> getMyNextMessages(String loggedUserId, String minVal);
}

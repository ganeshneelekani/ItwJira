package com.agileidc.itw.dao;


import java.util.List;

import com.agileidc.itw.model.ItwMessage;

/**
 * @author : Rajeev Yagati
 * @version : 1.0 20-1-2014
 * @since : 1.0
 */
public interface ItwMessageDao {
/**
* This is the method to be used to send the message
* to a user by passing user's userid as receiverid, message and logged-in userid as senderid .
*/
public void sendMessageToThisUser(String receiverId, String message, String senderId,Integer issueId);
public void sendMessageToThisUserOnly(String receiverId, String message,
		String senderId, Integer issueId);
/**
* This is the method to be used to get the messages
* of a logged user by passing user's userid as loggedUserid .
*/
public List<ItwMessage> getMyLatestMessages(String loggedUserId,Integer issueId);
public List<ItwMessage> getAllMessagesForId(String loggedUserId, Integer issueId);
/**
* This is the method to be used to get the previous messages
* of a logged user by passing user's userid as loggedUserid .
*/
public List<ItwMessage> getMyPrevMessages(String loggedUserId, String minVal);
/**
* This is the method to be used to get the next messages
* of a logged user by passing user's userid as loggedUserid .
*/
public List<ItwMessage> getMyNextMessages(String loggedUserId, String minVal);
}
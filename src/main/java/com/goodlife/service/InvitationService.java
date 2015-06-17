package com.goodlife.service;

import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;

public interface InvitationService {

	public void inviteUserByUsername(String username, String loggedInUser)
			throws UserAlreadyExistsException, UserNotFoundException;

	public void deleteUser(String username) throws UserNotFoundException;

	public void disableUser(String username) throws UserNotFoundException;

	public void enableUser(String username) throws UserNotFoundException;

	public void resendInvitation(String username, boolean resetPassword) throws UserAlreadyExistsException, UserNotFoundException;
}

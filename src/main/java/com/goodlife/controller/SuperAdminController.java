package com.goodlife.controller;

import java.security.Principal;



//Import log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goodlife.service.InvitationService;

@Controller
public class SuperAdminController {
	static final Logger logger = LogManager
			.getLogger(SuperAdminController.class.getName());

	@Autowired
	InvitationService invitationService;

	@RequestMapping(value = "secured/su/SendInvite", method = RequestMethod.GET)
	public String sentNewInvitation(ModelMap model, Principal principal) {
		return "landing/invite";
	}

	@RequestMapping(value = "secured/su/addUser", method = RequestMethod.POST)
	public String addUserAndInvite(@ModelAttribute(value = "username") String username,
			BindingResult result) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String loggedInUser = authentication.getName();
			invitationService.inviteUserByUsername(username, loggedInUser);
		} catch (Exception e) {
			//need to do something if we get an exception.
		}
		logger.debug("Adding User");
		return "landing/inviteSent";
	}

	@RequestMapping(value = "secured/su/delete", method = RequestMethod.GET)
	public String deleteUser(ModelMap model, Principal principal) {
		return "landing/delete";
	}

	@RequestMapping(value = "secured/su/deleteUser", method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute(value = "username") String username,
			BindingResult result) {
		try {
			invitationService.deleteUser(username);
		} catch (Exception e) {
			//need to do something if we get an exception.
		}
		logger.debug("Deleting User");
		return "landing/deleteConfirmation";
	}
	
	@RequestMapping(value = "secured/su/disable", method = RequestMethod.GET)
	public String disableUser(ModelMap model, Principal principal) {
		return "landing/disable";
	}
	
	@RequestMapping(value = "secured/su/disableUser", method = RequestMethod.POST)
	public String disableUser(@ModelAttribute(value = "username") String username,
			BindingResult result) {
		try {
			invitationService.disableUser(username);
		} catch (Exception e) {
			//need to do something if we get an exception.
		}
		logger.debug("Adding User");
		return "landing/disableConfirmation";
	}
	
	@RequestMapping(value = "secured/su/enable", method = RequestMethod.GET)
	public String enableUser(ModelMap model, Principal principal) {
		return "landing/enable";
	}
	
	@RequestMapping(value = "secured/su/enableUser", method = RequestMethod.POST)
	public String enableUser(@ModelAttribute(value = "username") String username,
			BindingResult result) {
		try {
			invitationService.enableUser(username);
		} catch (Exception e) {
			//need to do something if we get an exception.
		}
		logger.debug("Adding User");
		return "landing/enableConfirmation";
	}

}
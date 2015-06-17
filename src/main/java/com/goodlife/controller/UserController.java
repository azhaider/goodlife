package com.goodlife.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodlife.service.InvitationService;
import com.goodlife.service.UserService;

@Controller
public class UserController {
	static final Logger logger = LogManager.getLogger(UserController.class.getName());

	@Autowired
	InvitationService invitationService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "requestInvitationCode", method = RequestMethod.GET)
	public String sendNewInvitationCode(ModelMap model, Principal principal) {
		return "landing/requestInviteCode";
	}

	@RequestMapping(value = "resendInvitationCode", method = RequestMethod.POST)
	public String addUserAndInvite(@ModelAttribute(value = "username") String username,
			BindingResult result, ModelMap model) {
		try {
			invitationService.resendInvitation(username, false);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", e.getMessage());
			return "landing/requestInviteCode";
		}
		logger.debug("Resending invitation Code");
		return "landing/inviteSent";
	}
	
	@RequestMapping(value = "userSignUp", method = RequestMethod.GET)
	public String userSignup(ModelMap model, Principal principal) {
		return "landing/signup";
	}
	
	@RequestMapping(value = "finishUserSignUp", method = RequestMethod.POST)
	public String finishuserSignup(HttpServletRequest request, 
	        @RequestParam(value="email", required=false) String email, 
	        @RequestParam(value="pass1", required=false) String password1, 
	        @RequestParam(value="pass2", required=false) String password2, 
	        @RequestParam(value="token", required=false) String token, ModelMap model) throws Exception {
		
		//If the password does not match, return invalid token exception
		if (!password1.equals(password2)) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", "Password does not match with the confirm password field!!");
			return "landing/signup";
		} 
		
		try {
			logger.debug("Details: " + email + ":" + password1 + ":" + password2 + ":" + token);
			userService.activateAndUpdateUser(email, password1, token, false);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", e.getMessage());
			return "landing/signup";
		}
		logger.debug("User Activated and password updated.");
		return "landing/login";
	}
	
	// Implement Reset Password
	@RequestMapping(value = "resetPwdStepOne", method = RequestMethod.GET)
	public String resetPwdStepOne(ModelMap model, Principal principal) {
		return "landing/resetPasswdCode";
	}
	
	// Implement Reset Password
	@RequestMapping(value = "resetPwdStepTwo", method = RequestMethod.POST)
	public String resetPwdStepTwo(@ModelAttribute(value = "username") String username, ModelMap model, Principal principal) {
		try {
			invitationService.resendInvitation(username, true);
		} catch (Exception e) {
			//model.addAttribute("error", "true");
			//model.addAttribute("exceptionMessage", e.getMessage());
			//return "landing/resetPasswdCode";
		}
		logger.debug("Resending invitation Code");
		return "landing/resetPwdLastStep";
	}
	

	@RequestMapping(value = "resetPasswdComplete", method = RequestMethod.POST)
	public String resetPasswdComplete(HttpServletRequest request, 
	        @RequestParam(value="email", required=false) String email, 
	        @RequestParam(value="pass1", required=false) String password1, 
	        @RequestParam(value="pass2", required=false) String password2, 
	        @RequestParam(value="token", required=false) String token, ModelMap model) throws Exception {
		
		//If the password does not match, return invalid token exception
		if (!password1.equals(password2)) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", "Password does not match with the confirm password field!!");
			return "landing/resetPwdLastStep";
		} 
		
		try {
			logger.debug("Details: " + email + ":" + password1 + ":" + password2 + ":" + token);
			userService.activateAndUpdateUser(email, password1, token, true);
		} catch (Exception e) {
			model.addAttribute("error", "true");
			model.addAttribute("exceptionMessage", e.getMessage());
			return "landing/resetPwdLastStep";
		}
		logger.debug("User Activated and password updated.");
		return "landing/login";
	}
	
}
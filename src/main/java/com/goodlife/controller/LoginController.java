package com.goodlife.controller;

import java.security.Principal;

//Import log4j classes.
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	static final Logger logger = LogManager.getLogger(LoginController.class.getName());

	@RequestMapping(value = "/secured/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("username", name);
		logger.warn("Landing on the hello page");
		return "landing/hello";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		logger.debug("Login page hit");
		return "landing/login";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		logger.error("Error occured.  Sending back to login page");
		model.addAttribute("error", "true");
		return "landing/login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "landing/login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		logger.error("hitting the homepage");
		return "landing/index";
	}

}
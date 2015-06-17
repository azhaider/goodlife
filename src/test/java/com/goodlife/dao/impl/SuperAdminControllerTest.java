package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.ChapterController;
import com.goodlife.controller.SuperAdminController;
import com.goodlife.controller.UserManagementController;
import com.goodlife.dao.ChapterDAO;
import com.goodlife.dao.UserStatusDAO;
import com.goodlife.dao.UsersDAO;
import com.goodlife.exceptions.ChapterNotFoundException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Chapter;
import com.goodlife.model.Users;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class SuperAdminControllerTest {

	private static final Integer USER_ID = 1;
	private static final String USER_NAME = "dhaval";
	private static final Integer INV_CD = 123456;
	private static final char ROLE = 'S';
	private static final String EMAIL = "dhaval.raj@tsgforce.com";
	private static final String FNAME = "Dhaval";
	private static final String LNAME = "Raj";
	
	@Autowired
	private SuperAdminController superAdminController;
	
	@Mock
	private BindingResult mockResult;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(mockResult.hasErrors()).thenReturn(false);
	}

	@Test
	@Transactional
	public void addUserAndInvite(){
		String pageUrl = superAdminController.addUserAndInvite(USER_NAME, mockResult);
		assertEquals(pageUrl,"landing/inviteSent");
	}
}

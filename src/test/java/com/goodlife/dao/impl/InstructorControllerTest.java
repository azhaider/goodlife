package com.goodlife.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.goodlife.controller.InstructorController;
import com.goodlife.dao.InstructorDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class InstructorControllerTest {
	
	private static final String USER_NAME = "tom";
	private static final Integer USER_ID = 1;
	private static final Integer ROSTER_ID = 1;
	private static final Integer N_STDNT = 1;
	private static final Integer TOT_CAP = 2;
	
	@Autowired
	private InstructorDAO instructorDAO;
	
	@Autowired
	private InstructorController instructorController;
	
	@Test
	@Transactional
	public void testGetStudentProgress() {
		String studentProgress = instructorController.getStudentProgress(USER_ID, ROSTER_ID);
		System.out.println(studentProgress);
		assertTrue(Double.valueOf(studentProgress) > 0);
	}
	
	@Test
	@Transactional
	public void testFindInstructorByUserName(){
		String instructor = instructorController.findInstructorByUserId(USER_ID+1);
		System.out.println(instructor);
		assertTrue(instructor.length() > 2);
	}
	
	@Test
	@Transactional
	public void testFindInstructorByRosterId(){
		String instructor = instructorController.findInstructorByRosterId(ROSTER_ID);
		assertTrue(instructor.length() > 2);
		assertTrue(instructor.contains(String.valueOf(USER_ID)));
	}
	
	@Test
	@Transactional
	public void testAddStudentToRoster(){
		Boolean isSuccess = Boolean.valueOf(instructorController.addStudentToRoster(USER_ID + 2, ROSTER_ID));
		assertTrue(isSuccess);
		
		
	}
}

package com.goodlife.dao;

import java.util.List;

import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.Student;

public interface InstructorDAO {
	
	public Instructor findInstructorByUserId(Integer userId) throws UserNotFoundException;
	public Instructor findInstructorByRosterId(Integer rosterId) throws UserNotFoundException;
	public List<Student> findStudentsByRosterId(Integer rosterId);
	public Double getStudentProgress(Integer userId, Integer rosterId);
	public Integer addInstructor(Integer userId);
	// public Integer disableInstructor(String username) throws UserNotFoundException;
	// public Integer enableInstructor(String username) throws UserNotFoundException;
	// public void deleteInstructor(String username) throws UserNotFoundException;
	public Boolean addStudentToRoster(Integer userId, Integer rosterId);
	public Boolean changeRosterCapSize(Integer rosterId, Integer rosterSize);
	public Boolean removeStudentFromRoster(Integer userId, Integer rosterId);
}
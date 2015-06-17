package com.goodlife.service;

import java.util.List;

import com.goodlife.exceptions.InvalidEmailToken;
import com.goodlife.exceptions.UserAlreadyExistsException;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;
import com.goodlife.model.Student;
import com.goodlife.model.Users;

public interface UserService {

	public void activateAndUpdateUser(String email, String passwd, String token, boolean resetPassword)
			throws InvalidEmailToken, UserAlreadyExistsException, UserNotFoundException;
	
	public Users findByUserName(String username) throws UserNotFoundException;
	
	public List<Users> findByFirstName(String firstname) throws UserNotFoundException;
	
	public List<Users> findByLastName(String lastname) throws UserNotFoundException;
	
	public Users findByEmail(String email) throws UserNotFoundException;
	
	public List<Users> findByRoleTypes(List<Character> roles) throws UserNotFoundException;
	
	public List<Student> findStudentByRosterId(Integer rosterId) throws UserNotFoundException;
	
	public Instructor findInstructorByRosterId(Integer rosterId) throws UserNotFoundException;
	
	public List<Users> advancedQuery(String input, String field, List<Character> roles) 
			throws UserNotFoundException;
	//TODO
	//public Integer nFlags(String username);
}

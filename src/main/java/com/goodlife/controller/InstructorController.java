package com.goodlife.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.InstructorDAO;
import com.goodlife.exceptions.UserNotFoundException;
import com.goodlife.model.Instructor;

@Controller
@Transactional
@RequestMapping(value = "/instructor")
public class InstructorController {
	
	static final Logger logger = LogManager.getLogger(InstructorController.class.getName());
	
	@Autowired
	private InstructorDAO instructorDAO;
	
	@ResponseBody
	@RequestMapping(value = "/findinstructorbyuserid", method = RequestMethod.GET)
	public String findInstructorByUserId(@RequestParam(value = "userId") Integer userId){
		
		Instructor instructor;
		try {
			instructor = instructorDAO.findInstructorByUserId(userId);
		} catch (UserNotFoundException e1) {
			instructor = null;
			e1.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString((instructor == null) ? "" : instructor);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/findinstructorbyrosterid", method = RequestMethod.GET)
	public String findInstructorByRosterId(@RequestParam(value = "rosterId") Integer rosterId){
		
		Instructor instructor;
		try {
			instructor = instructorDAO.findInstructorByRosterId(rosterId);
		} catch (UserNotFoundException e1) {
			instructor = null;
			e1.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructor);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getstudentprogress", method = RequestMethod.GET)
	public String getStudentProgress(@RequestParam(value = "userId") Integer userId,
									 @RequestParam(value = "roserId") Integer rosterId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.getStudentProgress(userId, rosterId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/addinstructor", method = RequestMethod.GET)
	public String addInstructor(@RequestParam(value = "userId") Integer userId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.addInstructor(userId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addStudentToRoster", method = RequestMethod.GET)
	public String addStudentToRoster(@RequestParam(value = "userId") Integer userId,
									 @RequestParam(value = "rosterId") Integer rosterId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.addStudentToRoster(userId,rosterId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/removestudentfromroster", method = RequestMethod.GET)
	public String removeStudentFromRoster(@RequestParam("userId") Integer userId,
										  @RequestParam("rosterId") Integer rosterId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.removeStudentFromRoster(userId,rosterId));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
	
	@ResponseBody
	@RequestMapping(value = "/changerostercapsize", method = RequestMethod.GET)
	public String changeRosterCapSize(@RequestParam("rosterId") Integer rosterId,
									  @RequestParam("rosterSize") Integer rosterSize){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(instructorDAO.changeRosterCapSize(rosterId,rosterSize));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonResp;
	}
}

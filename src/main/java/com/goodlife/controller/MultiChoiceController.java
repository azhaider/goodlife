package com.goodlife.controller;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodlife.dao.MultiChoiceOptionDAO;
import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.model.MultiChoiceOption;
import com.goodlife.model.MultiChoiceQ;

@Controller
@RequestMapping(value = "/multichoice")
public class MultiChoiceController {
	
	static final Logger logger = LogManager.getLogger(MultiChoiceController.class.getName());
	
	@Autowired
	private MultiChoiceQDAO mcQdao;
	
	@Autowired
	private MultiChoiceOptionDAO mcOptdao;
	
	@ResponseBody
	@RequestMapping(value = "/addmultichoicequestion", method = RequestMethod.GET)
	public String addMultiChoiceQuestion(@RequestParam(value="questionText") String questionText,
											 @RequestParam(value="subChapId") Integer subChapId,
											 @RequestParam(value="helpTxt") String helpTxt,
											 @RequestParam(value="corrAns") Integer corrAns,
											 @RequestParam(value="orderId") Integer orderId) {
		
		MultiChoiceQ mcQ = new MultiChoiceQ();
		mcQ.setQuesText(questionText);
		mcQ.setSubChapId(subChapId);
		mcQ.setHelpText(helpTxt);
		mcQ.setCorrectAnswer(corrAns);
		mcQ.setOrderId(orderId);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.addMultiChoice(mcQ));
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
	@RequestMapping(value = "/addmultichoiceoption", method = RequestMethod.GET)
	public String addMultiChoiceOption(@RequestParam(value="mcQId") Integer mcQId,
											 @RequestParam(value="choiceText") String choiceText) {
		
		MultiChoiceOption mcOpt = new MultiChoiceOption();
		mcOpt.setMultiQuesId(mcQId);
		mcOpt.setChoiceText(choiceText);

		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.addMultiChoiceOption(mcOpt));
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
	@RequestMapping(value = "/deletemultichoiceoption", method = RequestMethod.GET)
	public String deleteMultiChoiceOption(@RequestParam(value="Id") Integer mcOptId) {
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.deleteMultiChoiceOption(mcOptId));
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
	@RequestMapping(value = "/deletemultichoiceques", method = RequestMethod.GET)
	public String deleteMultiChoiceQuestion(@RequestParam(value="Id") Integer mcQId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.deleteMultiChoice(mcQId));
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
	@RequestMapping(value = "/listalloptionsbyquestion", method = RequestMethod.GET)
	public String listAllOptionsByQuestion(@RequestParam(value="quesId") Integer quesId) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.getMultiChoiceOptions(quesId));
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
	@RequestMapping(value = "/updateoptiontext", method = RequestMethod.GET)
	public String updateOptionText(@RequestParam(value="optionId") Integer optionId, 
			@RequestParam(value="optionText") String optionText) {
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.updateChoiceText(optionId, optionText));
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
	@RequestMapping(value = "/listallquestionbysubchapter", method = RequestMethod.GET)
	public String listAllQuestionBySubchapter(
			@RequestParam(value="subChapId") Integer subChapId){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.getAllMultiChoice(subChapId));
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
	@RequestMapping(value = "/updatequestionorder", method = RequestMethod.GET)
	public String updateQuestionOrder(
			@RequestParam(value="multiChoiceIdList") List<Integer> multiChoiceIdList){

		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.updateOrder(multiChoiceIdList));
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
	@RequestMapping(value = "/updatequestiontext", method = RequestMethod.GET)
	public String updateQuestionText(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="quesText") String quesText){
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.updateQuestionText(multiChoiceId, quesText));
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
	@RequestMapping(value = "/updatehelptext", method = RequestMethod.GET)
	public String updateHelpText(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="helpText") String helpText){
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.updateHelpText(multiChoiceId, helpText));
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
	@RequestMapping(value = "/updatecorrectanswer", method = RequestMethod.GET)
	public String updateCorrectAnswer(@RequestParam(value="multiChoiceId") Integer multiChoiceId, 
			@RequestParam(value="quesText") Integer correctAnswer){
				
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.updateCorrectAnswer(multiChoiceId, correctAnswer));
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
	@RequestMapping(value = "/setmultichoiceqpublished", method = RequestMethod.GET)
	public String setMultiChoiceQPublished(@RequestParam(value = "multiChoiceId") Integer multiChoiceId,
										   @RequestParam(value = "published") Boolean published){
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcQdao.setPublishMultiChoiceQ(multiChoiceId, published));
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
	@RequestMapping(value = "/setmultichoiceoptpublished", method = RequestMethod.GET)
	public String setMultiChoiceOptPublished(@RequestParam(value = "optionId") Integer optionId,
										   @RequestParam(value = "published") Boolean published){
		ObjectMapper mapper = new ObjectMapper();
		String jsonResp ="";
		
		try {
			jsonResp = mapper.writeValueAsString(mcOptdao.setPublishMulitChoiceOption(optionId, published));
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

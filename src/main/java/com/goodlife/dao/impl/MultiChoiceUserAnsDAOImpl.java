package com.goodlife.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goodlife.dao.MultiChoiceQDAO;
import com.goodlife.dao.MultiChoiceUserAnsDAO;
import com.goodlife.exceptions.MultipleChoiceNotFoundException;
import com.goodlife.model.MultiChoiceQ;
import com.goodlife.model.MultiChoiceUserAns;

@Repository
public class MultiChoiceUserAnsDAOImpl implements MultiChoiceUserAnsDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private MultiChoiceQDAO multiChoiceQDAO;
	
	@Override
	public Boolean addMultiChoiceAnswer(MultiChoiceUserAns multiChoiceAns){
		
		MultiChoiceQ multiQ;
		try {
			multiQ = multiChoiceQDAO.getMultiChoiceQById(multiChoiceAns.getMultiQuesId());
		} catch (MultipleChoiceNotFoundException e) {
			multiQ = null;
			e.printStackTrace();
		}
			
		if(multiQ == null)
			return Boolean.FALSE;
		else{
			this.sessionFactory.getCurrentSession().saveOrUpdate(multiChoiceAns);
			return Boolean.TRUE;
		}
		
	}

	@Override
	public Integer getUserAnswer(Integer userId, Integer multiQuesId) {
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceUserAns.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("multiQuesId", multiQuesId),Restrictions.eqOrIsNull("userId", userId)));
		MultiChoiceUserAns multiChoiceAns = (MultiChoiceUserAns) criteria.uniqueResult();
		if(multiChoiceAns == null)
			return null;
		else
			return multiChoiceAns.getUserAnswer();
	}

	@Override
	public Boolean isMultiChoiceCorrect(Integer userId, Integer multiQuesId){
		Integer userAns = getUserAnswer(userId,multiQuesId);
		try {
			Integer corrAns = multiChoiceQDAO.getMultiChoiceQById(multiQuesId).getCorrectAnswer();
			if(userAns == null)
				return Boolean.FALSE;
			else
				return Boolean.valueOf(userAns == corrAns);
		} catch (MultipleChoiceNotFoundException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}
		
	}

	@Override
	public Boolean isMultiChoiceSubChapComplete(Integer userId, Integer subChapId) {
		Double correct = 0.0;
		Double total = 0.0;
		Boolean isComplete = Boolean.TRUE;
		List<MultiChoiceQ> multiChoiceQList = multiChoiceQDAO.getAllMultiChoice(subChapId);
		if(multiChoiceQList == null || multiChoiceQList.isEmpty() == true)
			isComplete = Boolean.FALSE;
		else{
			for(int i = 0; i < multiChoiceQList.size(); i++){
				total += 1.0;
				//userAns = getUserAnswer(userId,multiChoiceQList.get(i).getMultiQuesId());
				if(isMultiChoiceCorrect(userId,multiChoiceQList.get(i).getMultiQuesId()) == Boolean.TRUE)
					correct += 1.0;
			}
			if((correct/total) < 0.5)
				isComplete =  Boolean.FALSE;
		}
		return isComplete;
	}

	@Override
	public MultiChoiceUserAns getUserAnswerObj(Integer userId,
			Integer multiQuesId){
		
		Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(MultiChoiceUserAns.class);
		criteria.add(Restrictions.and(Restrictions.eqOrIsNull("multiQuesId", multiQuesId),Restrictions.eqOrIsNull("userId", userId)));
		MultiChoiceUserAns multiChoiceAnsObj = (MultiChoiceUserAns) criteria.uniqueResult();
		if(multiChoiceAnsObj == null)
			return null;
		else
			return multiChoiceAnsObj;
	}

}

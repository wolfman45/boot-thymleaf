package idu.cs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import idu.cs.domain.Question;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Override
	public Question getQuestionById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionsByuser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveQuestion(Question question) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateQuestion(Question question) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteQuestion(Question question) {
		// TODO Auto-generated method stub

	}

}

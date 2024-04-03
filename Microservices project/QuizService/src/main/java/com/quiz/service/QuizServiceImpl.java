package com.quiz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.entities.Quiz;
import com.quiz.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizrepository;
	@Autowired
	private QuestionClient questionClient;
	
	@Override
	public Quiz add(Quiz quiz) {
		return quizrepository.save(quiz);
	}

	@Override
	public List<Quiz> get() {
		
		List<Quiz> quizzes=quizrepository.findAll();
		
		List<Quiz> newQuizList=quizzes.stream().map(quiz->{
			quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
			return quiz;
		}).collect(Collectors.toList());
		
		return newQuizList;
	}

	@Override
	public Quiz getQuizById(Long id) {
		
		Quiz quiz=quizrepository.findById(id).get();
		quiz.setQuestions(questionClient.getQuestionOfQuiz(quiz.getId()));
		return quiz;
	}
	
	

}

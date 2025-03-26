package com.quizApp.service;

import com.quizApp.controller.QuizController;
import com.quizApp.dto.QuizDto;
import com.quizApp.entity.Question;
import com.quizApp.entity.Quiz;
import com.quizApp.exceptions.ResourceNotFoundException;
import com.quizApp.repository.QuestionRepository;
import com.quizApp.repository.QuizRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    private static final Logger log = LoggerFactory.getLogger(QuizController.class); // Declare Logger


    private  final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        Quiz quizSaved = quizRepository.save(quiz);
        return modelMapper.map(quizSaved, QuizDto.class);
    }

    @Override
    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(quiz -> modelMapper.map(quiz,QuizDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuizDto getQuizById(Long id) {
        log.info("getting quiz by id {}", id);
        Quiz quiz = quizRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("quiz not found with id:"+id));
        return modelMapper.map(quiz, QuizDto.class);
    }

    @Override
    public QuizDto updateQuizById(Long id, QuizDto quizDto) {
        log.info("updating quiz by id {}", id);
        Quiz quiz = quizRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("quiz not found with id:"+id));
        modelMapper.map(quizDto, quiz);
        quiz.setId(id);
        quiz = quizRepository.save(quiz);
        return modelMapper.map(quiz, QuizDto.class);
    }

    @Override
    public void deleteQuizById(Long id) {
        log.info("deleting quiz by id {}", id);
        Quiz quiz = quizRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("quiz not found with id:"+id));
        for (Question question : quiz.getQuestions()) {
            questionRepository.delete(question);

        }

        quizRepository.delete(quiz);
    }
}

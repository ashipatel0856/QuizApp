package com.quizApp.service;

import com.quizApp.controller.QuestionController;
import com.quizApp.dto.QuestionDto;
import com.quizApp.entity.Question;
import com.quizApp.exceptions.ResourceNotFoundException;
import com.quizApp.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public QuestionServiceImpl(QuestionRepository questionRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuestionDto createQuestion(QuestionDto questionDto) {
        log.info("Creating new questions: {}");
        Question newQuestion = modelMapper.map(questionDto, Question.class);
        Question question = questionRepository.save(newQuestion);
        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public QuestionDto getQuestionById(Long id) {
        log.info("Getting question by id: {}", id);
        Question question = questionRepository
                .findById(id)
                .orElseThrow(() -> new ResolutionException("question not found with id:{}"+id));

        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        log.info("Getting all questions");
        return questionRepository
                .findAll()
                .stream()
                .map(question -> modelMapper.map(question,QuestionDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public QuestionDto updateQuestionById(Long id ,QuestionDto questionDto) {
        log.info("updating question with id:{}"+id);
        Question question = questionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("questions not found with id"));
        modelMapper.map(questionDto, question);
        question.setId(id);
        question = questionRepository.save(question);
        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public void deleteQuestionById(Long id) {
        log.info("deleting question with id: {}", id);
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("question not found with id:{}"+id));

        questionRepository.delete(question);
    }
}

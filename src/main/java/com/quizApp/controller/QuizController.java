package com.quizApp.controller;

import com.quizApp.dto.QuizDto;
import com.quizApp.service.QuizService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private static final Logger log = LoggerFactory.getLogger(QuizController.class); // Declare Logger


    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<QuizDto> createQuiz(@RequestBody QuizDto quizDto) {
        QuizDto quizDto1 = quizService.createQuiz(quizDto);
        return new ResponseEntity<>(quizDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<QuizDto>> getAllQuiz() {
        log.info("getting all quiz");
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }
    @GetMapping("/{quizId}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable Long quizId) {
        log.info("getting quiz with id {}", quizId);
        QuizDto quizDto = quizService.getQuizById(quizId);
        return new ResponseEntity<>(quizDto, HttpStatus.OK);
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<QuizDto> updateQuizById(@PathVariable Long quizId, @RequestBody QuizDto quizDto) {
        log.info("updating quiz with id {}", quizId);
        QuizDto quizDto1 = quizService.updateQuizById(quizId, quizDto);
        return new ResponseEntity<>(quizDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<QuizDto> deleteQuizById(@PathVariable Long quizId) {
        log.info("deleting quiz with id {}", quizId);
        quizService.deleteQuizById(quizId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

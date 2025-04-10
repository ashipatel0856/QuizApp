package com.quizApp.controller;
import com.quizApp.dto.QuestionDto;
import com.quizApp.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ques")
public class QuestionController {

//    private static final Logger log = LoggerFactory.getLogger(QuizController.class);
    private final QuestionService questionService;


    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;

    }


//    @PostMapping(produces = "application/json", consumes = "application/json")
    @PostMapping("/create")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto) {
        QuestionDto questionDto1 = questionService.createQuestion(questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionDto1);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long questionId) {
        QuestionDto question = questionService.getQuestionById(questionId);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<QuestionDto> questions = questionService.getAllQuestions();
        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionDto> updateQuestionById(@PathVariable Long questionId, @RequestBody QuestionDto questionDto) {
        QuestionDto questionDto1 = questionService.updateQuestionById(questionId, questionDto);
        return ResponseEntity.status(HttpStatus.OK).body(questionDto1);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<QuestionDto> deleteQuestionById(@PathVariable Long questionId) {
        questionService.deleteQuestionById(questionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

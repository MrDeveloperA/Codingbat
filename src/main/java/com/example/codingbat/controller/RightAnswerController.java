package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.RightAnswer;
import com.example.codingbat.service.RightAnswerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rightAnswer")

public class RightAnswerController {
    @Autowired
    RightAnswerService rightAnswerService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addRightAnswer(@Valid @RequestBody RightAnswer rightAnswer) {
        ApiResponse apiResponse = rightAnswerService.addRightAnswer(rightAnswer);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<RightAnswer>> getRightAnswer() {
        List<RightAnswer> rightAnswers = rightAnswerService.getRightAnswer();
        return ResponseEntity.ok(rightAnswers);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<RightAnswer> getRightAnswerById(@PathVariable Integer id) {
        RightAnswer rightAnswerById = rightAnswerService.getRightAnswerById(id);
        return ResponseEntity.ok(rightAnswerById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editRightAnswer(@PathVariable Integer id, @Valid @RequestBody RightAnswer rightAnswer) {
        ApiResponse apiResponse = rightAnswerService.editRightAnswer(id, rightAnswer);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRightAnswer(@PathVariable Integer id) {
        ApiResponse apiResponse = rightAnswerService.deleteRightAnswer(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = ((FieldError) error).getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

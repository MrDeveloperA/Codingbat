package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Privacy;
import com.example.codingbat.entity.ProblemText;
import com.example.codingbat.service.PrivacyService;
import com.example.codingbat.service.ProblemTextService;
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
@RequestMapping("/api/problemText")

public class ProblemTextController {
    @Autowired
    ProblemTextService problemTextService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addProblemText(@Valid @RequestBody ProblemText problemText) {
        ApiResponse apiResponse = problemTextService.addProblemText(problemText);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<ProblemText>> getProblemText() {
        List<ProblemText> problemText = problemTextService.getProblemText();
        return ResponseEntity.ok(problemText);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ProblemText> getProblemTextById(@PathVariable Integer id) {
        ProblemText problemTextById = problemTextService.getProblemTextById(id);
        return ResponseEntity.ok(problemTextById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editProblemText(@PathVariable Integer id, @Valid @RequestBody ProblemText problemText) {
        ApiResponse apiResponse = problemTextService.editProblemText(id, problemText);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProblemText(@PathVariable Integer id) {
        ApiResponse apiResponse = problemTextService.deleteProblemText(id);
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

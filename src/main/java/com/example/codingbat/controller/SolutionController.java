package com.example.codingbat.controller;

import com.example.codingbat.diler.AnswerDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.SolutionDto;
import com.example.codingbat.entity.Answer;
import com.example.codingbat.entity.Solution;
import com.example.codingbat.service.AnswerService;
import com.example.codingbat.service.SolutionService;
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
@RequestMapping("/api/solution")

public class SolutionController {
    @Autowired
    SolutionService solutionService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addSolution(@Valid @RequestBody SolutionDto solutionDto) {
        ApiResponse apiResponse = solutionService.addSolution(solutionDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Solution>> getSolution() {
        List<Solution> solutions = solutionService.getSolution();
        return ResponseEntity.ok(solutions);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Solution> getSolutionById(@PathVariable Integer id) {
        Solution solutionById = solutionService.getSolutionById(id);
        return ResponseEntity.ok(solutionById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editSolution(@PathVariable Integer id, @Valid @RequestBody SolutionDto solutionDto) {
        ApiResponse apiResponse = solutionService.editSolution(id, solutionDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSolution(@PathVariable Integer id) {
        ApiResponse apiResponse = solutionService.deleteSolution(id);
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

package com.example.codingbat.controller;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.Condition;
import com.example.codingbat.service.AboutService;
import com.example.codingbat.service.ConditionService;
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
@RequestMapping("/api/condition")

public class ConditionController {
    @Autowired
    ConditionService conditionService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addCondition(@Valid @RequestBody Condition condition) {
        ApiResponse apiResponse = conditionService.addCondition(condition);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Condition>> getCondition() {
        List<Condition> conditions = conditionService.getCondition();
        return ResponseEntity.ok(conditions);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Condition> getConditionById(@PathVariable Integer id) {
        Condition conditionById = conditionService.getConditionById(id);
        return ResponseEntity.ok(conditionById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCondition(@PathVariable Integer id, @Valid @RequestBody Condition condition) {
        ApiResponse apiResponse = conditionService.editCondition(id, condition);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCondition(@PathVariable Integer id) {
        ApiResponse apiResponse = conditionService.deleteCondition(id);
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

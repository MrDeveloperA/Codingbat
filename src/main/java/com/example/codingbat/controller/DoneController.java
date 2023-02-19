package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Condition;
import com.example.codingbat.entity.Done;
import com.example.codingbat.repository.DoneRepository;
import com.example.codingbat.service.ConditionService;
import com.example.codingbat.service.DoneService;
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
@RequestMapping("/api/done")

public class DoneController {
    @Autowired
    DoneService doneService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addDone(@Valid @RequestBody Done done) {
        ApiResponse apiResponse = doneService.addDone(done);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Done>> getDone() {
        List<Done> done = doneService.getDone();
        return ResponseEntity.ok(done);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Done> getDoneById(@PathVariable Integer id) {
        Done doneById = doneService.getDoneById(id);
        return ResponseEntity.ok(doneById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editDone(@PathVariable Integer id, @Valid @RequestBody Done done) {
        ApiResponse apiResponse = doneService.editDone(id, done);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDone(@PathVariable Integer id) {
        ApiResponse apiResponse = doneService.deleteDone(id);
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

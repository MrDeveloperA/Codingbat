package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Declare;
import com.example.codingbat.service.DeclareService;
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
@RequestMapping("/api/declare")

public class DeclareController {
    @Autowired
    DeclareService declareService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addDeclare(@Valid @RequestBody Declare declare) {
        ApiResponse apiResponse = declareService.addDeclare(declare);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Declare>> getDeclare() {
        List<Declare> declares = declareService.getDeclare();
        return ResponseEntity.ok(declares);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Declare> getDeclareById(@PathVariable Integer id) {
        Declare declareById = declareService.getDeclareById(id);
        return ResponseEntity.ok(declareById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editDeclare(@PathVariable Integer id, @Valid @RequestBody Declare declare) {
        ApiResponse apiResponse = declareService.editDeclare(id, declare);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAnswer(@PathVariable Integer id) {
        ApiResponse apiResponse = declareService.deleteDeclare(id);
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

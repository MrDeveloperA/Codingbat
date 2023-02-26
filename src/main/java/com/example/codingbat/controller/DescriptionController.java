package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Description;
import com.example.codingbat.service.DescriptionService;
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
@RequestMapping("/api/description")

public class DescriptionController {
    @Autowired
    DescriptionService descriptionService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addDescription(@Valid @RequestBody Description description) {
        ApiResponse apiResponse = descriptionService.addDescription(description);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Description>> getDescription() {
        List<Description> descriptions = descriptionService.getDescription();
        return ResponseEntity.ok(descriptions);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Description> getDescriptionById(@PathVariable Integer id) {
        Description descriptionById = descriptionService.getDescriptionById(id);
        return ResponseEntity.ok(descriptionById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editDescription(@PathVariable Integer id, @Valid @RequestBody Description description) {
        ApiResponse apiResponse = descriptionService.editDescription(id, description);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDescription(@PathVariable Integer id) {
        ApiResponse apiResponse = descriptionService.deleteDescription(id);
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

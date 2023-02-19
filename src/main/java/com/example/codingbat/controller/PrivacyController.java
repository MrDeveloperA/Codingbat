package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.Privacy;
import com.example.codingbat.service.DoneService;
import com.example.codingbat.service.PrivacyService;
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
@RequestMapping("/api/privacy")

public class PrivacyController {
    @Autowired
    PrivacyService privacyService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addPrivacy(@Valid @RequestBody Privacy privacy) {
        ApiResponse apiResponse = privacyService.addPrivacy(privacy);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Privacy>> getPrivacy() {
        List<Privacy> privacy = privacyService.getPrivacy();
        return ResponseEntity.ok(privacy);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Privacy> getPrivacyById(@PathVariable Integer id) {
        Privacy privacyById = privacyService.getPrivacyById(id);
        return ResponseEntity.ok(privacyById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editPrivacy(@PathVariable Integer id, @Valid @RequestBody Privacy privacy) {
        ApiResponse apiResponse = privacyService.editPrivacy(id, privacy);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePrivacy(@PathVariable Integer id) {
        ApiResponse apiResponse = privacyService.deletePrivacy(id);
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

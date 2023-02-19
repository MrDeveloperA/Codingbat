package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Help;
import com.example.codingbat.entity.InnerTheme;
import com.example.codingbat.service.HelpService;
import com.example.codingbat.service.InnerThemeService;
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
@RequestMapping("/api/innerTheme")

public class InnerThemeController {
    @Autowired
    InnerThemeService innerThemeService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addInnerTheme(@Valid @RequestBody InnerTheme innerTheme) {
        ApiResponse apiResponse = innerThemeService.addInnerTheme(innerTheme);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<InnerTheme>> getInnerTheme() {
        List<InnerTheme> innerThemes = innerThemeService.getInnerTheme();
        return ResponseEntity.ok(innerThemes);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<InnerTheme> getInnerThemeById(@PathVariable Integer id) {
        InnerTheme innerThemeById = innerThemeService.getInnerThemeById(id);
        return ResponseEntity.ok(innerThemeById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editInnerTheme(@PathVariable Integer id, @Valid @RequestBody InnerTheme innerTheme) {
        ApiResponse apiResponse = innerThemeService.editInnerTheme(id, innerTheme);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInnerTheme(@PathVariable Integer id) {
        ApiResponse apiResponse = innerThemeService.deleteInnerTheme(id);
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

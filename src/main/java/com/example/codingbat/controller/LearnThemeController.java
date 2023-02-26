package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.LearnThemeDto;
import com.example.codingbat.entity.LearnThemes;
import com.example.codingbat.service.LearnThemeService;
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
@RequestMapping("/api/learnTheme")

public class LearnThemeController {
    @Autowired
    LearnThemeService learnThemeService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addLearnTheme(@Valid @RequestBody LearnThemeDto learnThemeDto) {
        ApiResponse apiResponse = learnThemeService.addLearnTheme(learnThemeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<LearnThemes>> getLearnTheme() {
        List<LearnThemes> learnThemes = learnThemeService.getLearnThemes();
        return ResponseEntity.ok(learnThemes);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<LearnThemes> getLearnThemeById(@PathVariable Integer id) {
        LearnThemes learnThemeById = learnThemeService.getLearnThemeById(id);
        return ResponseEntity.ok(learnThemeById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLearnTheme(@PathVariable Integer id, @Valid @RequestBody LearnThemeDto learnThemeDto) {
        ApiResponse apiResponse = learnThemeService.editLearnThemes(id, learnThemeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLearnTheme(@PathVariable Integer id) {
        ApiResponse apiResponse = learnThemeService.deleteLearnTheme(id);
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

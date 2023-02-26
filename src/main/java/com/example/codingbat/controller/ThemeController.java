package com.example.codingbat.controller;

import com.example.codingbat.diler.AnswerDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.ThemeDto;
import com.example.codingbat.entity.Answer;
import com.example.codingbat.entity.Theme;
import com.example.codingbat.service.AnswerService;
import com.example.codingbat.service.ThemeService;
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
@RequestMapping("/api/theme")

public class ThemeController {
    @Autowired
    ThemeService themeService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addTheme(@Valid @RequestBody ThemeDto themeDto) {
        ApiResponse apiResponse = themeService.addTheme(themeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Theme>> getTheme() {
        List<Theme> themes = themeService.getTheme();
        return ResponseEntity.ok(themes);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Theme> getThemeById(@PathVariable Integer id) {
        Theme themeById = themeService.getThemeById(id);
        return ResponseEntity.ok(themeById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editTheme(@PathVariable Integer id, @Valid @RequestBody ThemeDto themeDto) {
        ApiResponse apiResponse = themeService.editTheme(id, themeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTheme(@PathVariable Integer id) {
        ApiResponse apiResponse = themeService.deleteTheme(id);
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

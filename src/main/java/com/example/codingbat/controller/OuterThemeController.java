package com.example.codingbat.controller;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.OuterThemeDto;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.OuterTheme;
import com.example.codingbat.service.AboutService;
import com.example.codingbat.service.OuterThemeService;
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
@RequestMapping("/api/outerTheme")

public class OuterThemeController {
    @Autowired
    OuterThemeService outerThemeService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addOuterTheme(@Valid @RequestBody OuterThemeDto outerThemeDto) {
        ApiResponse apiResponse = outerThemeService.addThemeToPractice(outerThemeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<OuterTheme>> getOuterTheme() {
        List<OuterTheme> outerTheme = outerThemeService.getOuterTheme();
        return ResponseEntity.ok(outerTheme);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<OuterTheme> getOuterThemeById(@PathVariable Integer id) {
        OuterTheme outerThemeById = outerThemeService.getOuterThemeById(id);
        return ResponseEntity.ok(outerThemeById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editOuterTheme(@PathVariable Integer id, @Valid @RequestBody OuterThemeDto outerThemeDto) {
        ApiResponse apiResponse = outerThemeService.editOuterTheme(id, outerThemeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOuterTheme(@PathVariable Integer id) {
        ApiResponse apiResponse = outerThemeService.deleteOuterTheme(id);
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

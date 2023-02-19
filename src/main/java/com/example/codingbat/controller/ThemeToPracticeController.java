package com.example.codingbat.controller;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.ThemeToPracticeDto;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.ThemeToPractice;
import com.example.codingbat.service.AboutService;
import com.example.codingbat.service.ThemeToPracticeService;
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
@RequestMapping("/api/themeToPractice")

public class ThemeToPracticeController {
    @Autowired
    ThemeToPracticeService themeToPracticeService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addThemToPractice(@Valid @RequestBody ThemeToPracticeDto themeToPracticeDto) {
        ApiResponse apiResponse = themeToPracticeService.addThemePractice(themeToPracticeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<ThemeToPractice>> getThemeToPractice() {
        List<ThemeToPractice> themeToPractices = themeToPracticeService.getThemeToPractice();
        return ResponseEntity.ok(themeToPractices);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ThemeToPractice> getThemeToPracticeById(@PathVariable Integer id) {
        ThemeToPractice themeToPracticeById = themeToPracticeService.getThemeToPracticeById(id);
        return ResponseEntity.ok(themeToPracticeById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editThemeToPractice(@PathVariable Integer id, @Valid @RequestBody ThemeToPracticeDto themeToPracticeDto) {
        ApiResponse apiResponse = themeToPracticeService.editThemeToPractice(id, themeToPracticeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteThemeToPractice(@PathVariable Integer id) {
        ApiResponse apiResponse = themeToPracticeService.deleteThemeToPractice(id);
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

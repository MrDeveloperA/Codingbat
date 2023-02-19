package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.MiscCodePractice;
import com.example.codingbat.entity.MiscCodeTheme;
import com.example.codingbat.service.DoneService;
import com.example.codingbat.service.MiscCodeThemeService;
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
@RequestMapping("/api/miscCodeTheme")

public class MiscCodeThemeController {
    @Autowired
    MiscCodeThemeService miscCodeThemeService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addMiscCodeTheme(@Valid @RequestBody MiscCodeTheme miscCodeTheme) {
        ApiResponse apiResponse = miscCodeThemeService.addMiscCodeTheme(miscCodeTheme);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<MiscCodeTheme>> getMiscCodeTheme() {
        List<MiscCodeTheme> miscCodeThemes = miscCodeThemeService.getMiscCodeTheme();
        return ResponseEntity.ok(miscCodeThemes);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<MiscCodeTheme> getMiscCodeThemeById(@PathVariable Integer id) {
        MiscCodeTheme miscCodeThemeById = miscCodeThemeService.getMiscCodeThemeById(id);
        return ResponseEntity.ok(miscCodeThemeById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editMiscCodeTheme(@PathVariable Integer id, @Valid @RequestBody MiscCodeTheme miscCodeTheme) {
        ApiResponse apiResponse = miscCodeThemeService.editMiscCodeTheme(id, miscCodeTheme);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMiscCodeTheme(@PathVariable Integer id) {
        ApiResponse apiResponse = miscCodeThemeService.deleteMiscCodeTheme(id);
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

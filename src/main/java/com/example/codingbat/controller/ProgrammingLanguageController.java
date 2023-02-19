package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.ProblemText;
import com.example.codingbat.entity.ProgrammingLanguage;
import com.example.codingbat.service.ProblemTextService;
import com.example.codingbat.service.ProgrammingLanguageService;
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
@RequestMapping("/api/programmingLanguage")

public class ProgrammingLanguageController {
    @Autowired
    ProgrammingLanguageService programmingLanguageService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addPrLanguage(@Valid @RequestBody ProgrammingLanguage programmingLanguage) {
        ApiResponse apiResponse = programmingLanguageService.addPrLanguageText(programmingLanguage);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<ProgrammingLanguage>> getPrLanguage() {
        List<ProgrammingLanguage> programmingLanguages = programmingLanguageService.getProgrammingLanguage();
        return ResponseEntity.ok(programmingLanguages);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> getProgrammingLanguageById(@PathVariable Integer id) {
        ProgrammingLanguage programmingLanguageById = programmingLanguageService.getPrLanguageTextById(id);
        return ResponseEntity.ok(programmingLanguageById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editPrLanguage(@PathVariable Integer id, @Valid @RequestBody ProgrammingLanguage programmingLanguage) {
        ApiResponse apiResponse = programmingLanguageService.editPrLanguage(id, programmingLanguage);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePrLanguage(@PathVariable Integer id) {
        ApiResponse apiResponse = programmingLanguageService.deletePrLanguage(id);
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

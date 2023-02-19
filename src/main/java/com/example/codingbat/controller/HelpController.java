package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.Help;
import com.example.codingbat.repository.HelpRepository;
import com.example.codingbat.service.DoneService;
import com.example.codingbat.service.HelpService;
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
@RequestMapping("/api/help")

public class HelpController {
    @Autowired
    HelpService helpService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addHelp(@Valid @RequestBody Help help) {
        ApiResponse apiResponse = helpService.addHelp(help);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Help>> getHelp() {
        List<Help> helps = helpService.getHelp();
        return ResponseEntity.ok(helps);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Help> getHelpById(@PathVariable Integer id) {
        Help helpById = helpService.getHelpById(id);
        return ResponseEntity.ok(helpById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editHelp(@PathVariable Integer id, @Valid @RequestBody Help help) {
        ApiResponse apiResponse = helpService.editHelp(id, help);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteHelp(@PathVariable Integer id) {
        ApiResponse apiResponse = helpService.deleteHelp(id);
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

package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.GivenCode;
import com.example.codingbat.service.DoneService;
import com.example.codingbat.service.GivenCodeService;
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
@RequestMapping("/api/givenCode")

public class GivenCodeController {
    @Autowired
    GivenCodeService givenCodeService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addGivenCode(@Valid @RequestBody GivenCode givenCode) {
        ApiResponse apiResponse = givenCodeService.addGivenCode(givenCode);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<GivenCode>> getGivenCode() {
        List<GivenCode> givenCodes = givenCodeService.getGivenCode();
        return ResponseEntity.ok(givenCodes);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<GivenCode> getGivenCodeById(@PathVariable Integer id) {
        GivenCode givenCodeById = givenCodeService.getGivenCodeById(id);
        return ResponseEntity.ok(givenCodeById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editGivenCode(@PathVariable Integer id, @Valid @RequestBody GivenCode givenCode) {
        ApiResponse apiResponse = givenCodeService.editGivenCode(id, givenCode);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteGivenCode(@PathVariable Integer id) {
        ApiResponse apiResponse = givenCodeService.deleteGivenCode(id);
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

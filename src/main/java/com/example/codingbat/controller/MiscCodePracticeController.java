package com.example.codingbat.controller;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.MiscCodePracticeDto;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.MiscCodePractice;
import com.example.codingbat.service.AboutService;
import com.example.codingbat.service.MiscCodePracticeService;
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
@RequestMapping("/api/miscCodePractice")

public class MiscCodePracticeController {
    @Autowired
    MiscCodePracticeService miscCodePracticeService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addMiscCodePractice(@Valid @RequestBody MiscCodePracticeDto miscCodePracticeDto) {
        ApiResponse apiResponse = miscCodePracticeService.addMiscCodePractice(miscCodePracticeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<MiscCodePractice>> getMiscCodePractice() {
        List<MiscCodePractice> miscCodePractices = miscCodePracticeService.getMiscCodePractice();
        return ResponseEntity.ok(miscCodePractices);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<MiscCodePractice> getMiscCodePracticeById(@PathVariable Integer id) {
        MiscCodePractice miscCodePracticeById = miscCodePracticeService.getMiscCodePracticeById(id);
        return ResponseEntity.ok(miscCodePracticeById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editMiscCodePractice(@PathVariable Integer id, @Valid @RequestBody MiscCodePracticeDto miscCodePracticeDto) {
        ApiResponse apiResponse = miscCodePracticeService.editMiscCodePractice(id, miscCodePracticeDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMiscCodePractice(@PathVariable Integer id) {
        ApiResponse apiResponse = miscCodePracticeService.deleteMiscCodePractice(id);
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

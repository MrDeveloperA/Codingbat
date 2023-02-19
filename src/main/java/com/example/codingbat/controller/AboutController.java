package com.example.codingbat.controller;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.About;
import com.example.codingbat.service.AboutService;
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
@RequestMapping("/api/about")

public class AboutController {
    @Autowired
    AboutService aboutService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addAbout(@Valid @RequestBody AboutDto aboutDto) {
        ApiResponse apiResponse = aboutService.addAbout(aboutDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<About>> getAbout() {
        List<About> about = aboutService.getAbout();
        return ResponseEntity.ok(about);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<About> getAboutById(@PathVariable Integer id) {
        About aboutById = aboutService.getAboutById(id);
        return ResponseEntity.ok(aboutById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editAbout(@PathVariable Integer id, @Valid @RequestBody AboutDto aboutDto) {
        ApiResponse apiResponse = aboutService.editAbout(id, aboutDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAbout(@PathVariable Integer id) {
        ApiResponse apiResponse = aboutService.deleteAbout(id);
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

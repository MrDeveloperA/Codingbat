package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.InnerThemeWithVideoDto;
import com.example.codingbat.entity.InnerTheme;
import com.example.codingbat.entity.InnerThemeWithVideo;
import com.example.codingbat.service.InnerThemeService;
import com.example.codingbat.service.InnerThemeWithVideoService;
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
@RequestMapping("/api/innerThemeWithVideo")

public class InnerThemeWithVideoController {
    @Autowired
    InnerThemeWithVideoService innerThemeWithVideoService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addInnerThemeWithVideo(@Valid @RequestBody InnerThemeWithVideoDto innerThemeWithVideoDto) {
        ApiResponse apiResponse = innerThemeWithVideoService.addInnerThemeWithVideo(innerThemeWithVideoDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<InnerThemeWithVideo>> getInnerThemeWithVideo() {
        List<InnerThemeWithVideo> innerThemeWithVideos = innerThemeWithVideoService.getInnerThemeWithVideo();
        return ResponseEntity.ok(innerThemeWithVideos);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<InnerThemeWithVideo> getInnerThemeWithVideoById(@PathVariable Integer id) {
        InnerThemeWithVideo innerThemeWithVideoById = innerThemeWithVideoService.getInnerThemeWithVideoById(id);
        return ResponseEntity.ok(innerThemeWithVideoById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editInnerThemeWithVideo(@PathVariable Integer id, @Valid @RequestBody InnerThemeWithVideoDto innerThemeWithVideoDto) {
        ApiResponse apiResponse = innerThemeWithVideoService.editInnerThemeWithVideo(id, innerThemeWithVideoDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInnerThemeWithVideo(@PathVariable Integer id) {
        ApiResponse apiResponse = innerThemeWithVideoService.deleteInnerThemeWithVideo(id);
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

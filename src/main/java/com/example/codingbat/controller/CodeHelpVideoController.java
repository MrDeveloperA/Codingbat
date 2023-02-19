package com.example.codingbat.controller;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.CodeHelpVideoDto;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.CodeHelpVideo;
import com.example.codingbat.service.AboutService;
import com.example.codingbat.service.CodeHelpVideoService;
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
@RequestMapping("/api/codeHelpVideo")

public class CodeHelpVideoController {
    @Autowired
    CodeHelpVideoService codeHelpVideoService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addCodeHelpVideo(@Valid @RequestBody CodeHelpVideoDto codeHelpVideoDto) {
        ApiResponse apiResponse = codeHelpVideoService.addCodeHelpVideo(codeHelpVideoDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<CodeHelpVideo>> getCodeHelpVideo() {
        List<CodeHelpVideo> codeHelpVideos = codeHelpVideoService.getCodeHelpVideo();
        return ResponseEntity.ok(codeHelpVideos);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<CodeHelpVideo> getCodeHelpVideoById(@PathVariable Integer id) {
        CodeHelpVideo codeHelpVideoById = codeHelpVideoService.getCodeHelpVideoById(id);
        return ResponseEntity.ok(codeHelpVideoById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCodeHelpVideo(@PathVariable Integer id, @Valid @RequestBody CodeHelpVideoDto codeHelpVideoDto) {
        ApiResponse apiResponse = codeHelpVideoService.editCodeHelpVideo(id, codeHelpVideoDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCodeHelpVideo(@PathVariable Integer id) {
        ApiResponse apiResponse = codeHelpVideoService.deleteCodeHelpVideo(id);
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

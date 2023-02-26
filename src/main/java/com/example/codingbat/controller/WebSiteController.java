package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.UserDto;
import com.example.codingbat.diler.WebSiteDto;
import com.example.codingbat.entity.User;
import com.example.codingbat.entity.WebSite;
import com.example.codingbat.service.UserService;
import com.example.codingbat.service.WebSiteService;
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
@RequestMapping("/api/webSite")

public class WebSiteController {
    @Autowired
    WebSiteService webSiteService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addWebSite(@Valid @RequestBody WebSiteDto webSiteDto) {
        ApiResponse apiResponse = webSiteService.addWebSite(webSiteDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<WebSite>> getWebSite() {
        List<WebSite> webSites = webSiteService.getWebSite();
        return ResponseEntity.ok(webSites);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<WebSite> getWebSiteById(@PathVariable Integer id) {
        WebSite webSiteById = webSiteService.getWebSiteById(id);
        return ResponseEntity.ok(webSiteById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWebSite(@PathVariable Integer id, @Valid @RequestBody WebSiteDto webSiteDto) {
        ApiResponse apiResponse = webSiteService.editWebSite(id, webSiteDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWebSite(@PathVariable Integer id) {
        ApiResponse apiResponse = webSiteService.deleteWebSite(id);
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

package com.example.codingbat.controller;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.MainPageDto;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.MainPage;
import com.example.codingbat.service.AboutService;
import com.example.codingbat.service.MainPageService;
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
@RequestMapping("/api/mainPage")

public class MainPageController {
    @Autowired
    MainPageService mainPageService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addMainPage(@Valid @RequestBody MainPageDto mainPageDto) {
        ApiResponse apiResponse = mainPageService.addMainPage(mainPageDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<MainPage>> getMainPage() {
        List<MainPage> mainPage = mainPageService.getMainPage();
        return ResponseEntity.ok(mainPage);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<MainPage> getMainPageById(@PathVariable Integer id) {
        MainPage mainPageById = mainPageService.getMainPageById(id);
        return ResponseEntity.ok(mainPageById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editMainPage(@PathVariable Integer id, @Valid @RequestBody MainPageDto mainPageDto) {
        ApiResponse apiResponse = mainPageService.editMainPage(id, mainPageDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteMainPage(@PathVariable Integer id) {
        ApiResponse apiResponse = mainPageService.deleteMain(id);
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

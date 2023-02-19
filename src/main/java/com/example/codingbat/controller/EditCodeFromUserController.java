package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.EditCodeFromUser;
import com.example.codingbat.service.DoneService;
import com.example.codingbat.service.EditCodeFromUserService;
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
@RequestMapping("/api/editCodefromUser")

public class EditCodeFromUserController {
    @Autowired
    EditCodeFromUserService editCodeFromUserService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addEditCodeUser(@Valid @RequestBody EditCodeFromUser editCodeFromUser) {
        ApiResponse apiResponse = editCodeFromUserService.addEditCode(editCodeFromUser);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<EditCodeFromUser>> getEditCodeFromUser() {
        List<EditCodeFromUser> editCodeFromUserList  = editCodeFromUserService.getEditCodeFromUser();
        return ResponseEntity.ok(editCodeFromUserList);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<EditCodeFromUser> getEditCodeFromUserById(@PathVariable Integer id) {
        EditCodeFromUser editCodeFromUserById = editCodeFromUserService.getEditCodeFromUserById(id);
        return ResponseEntity.ok(editCodeFromUserById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCodeFromUser(@PathVariable Integer id, @Valid @RequestBody EditCodeFromUser editCodeFromUser) {
        ApiResponse apiResponse = editCodeFromUserService.editEditCodeFromUser(id, editCodeFromUser);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCodeFromUser(@PathVariable Integer id) {
        ApiResponse apiResponse = editCodeFromUserService.deleteEditCodeFromUser(id);
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

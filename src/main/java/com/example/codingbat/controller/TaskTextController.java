package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Task;
import com.example.codingbat.entity.TaskText;
import com.example.codingbat.service.TaskService;
import com.example.codingbat.service.TaskTextService;
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
@RequestMapping("/api/taskText")

public class TaskTextController {
    @Autowired
    TaskTextService taskTextService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addTaskText(@Valid @RequestBody TaskText taskText) {
        ApiResponse apiResponse = taskTextService.addTaskText(taskText);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<TaskText>> getTaskText() {
        List<TaskText> taskTexts = taskTextService.getTaskText();
        return ResponseEntity.ok(taskTexts);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<TaskText> getTaskTextById(@PathVariable Integer id) {
        TaskText taskTextById = taskTextService.getTaskTextById(id);
        return ResponseEntity.ok(taskTextById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editTaskText(@PathVariable Integer id, @Valid @RequestBody TaskText taskText) {
        ApiResponse apiResponse = taskTextService.editTaskText(id, taskText);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTaskText(@PathVariable Integer id) {
        ApiResponse apiResponse = taskTextService.deleteTaskText(id);
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

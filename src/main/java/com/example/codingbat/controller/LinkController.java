package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.Link;
import com.example.codingbat.service.DoneService;
import com.example.codingbat.service.LinkService;
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
@RequestMapping("/api/link")

public class LinkController {
    @Autowired
    LinkService linkService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addLink(@Valid @RequestBody Link link) {
        ApiResponse apiResponse = linkService.addLink(link);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Link>> getLink() {
        List<Link> links = linkService.getLink();
        return ResponseEntity.ok(links);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Link> getLinkById(@PathVariable Integer id) {
        Link linkById = linkService.getLinkById(id);
        return ResponseEntity.ok(linkById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLink(@PathVariable Integer id, @Valid @RequestBody Link link) {
        ApiResponse apiResponse = linkService.editLink(id, link);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLink(@PathVariable Integer id) {
        ApiResponse apiResponse = linkService.deleteLink(id);
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

package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.BlockDto;
import com.example.codingbat.entity.Block;
import com.example.codingbat.service.BlockService;
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
@RequestMapping("/api/block")

public class BlockController {
    @Autowired
    BlockService blockService;

    //    Create
    @PostMapping
    public ResponseEntity<ApiResponse> addBlock(@Valid @RequestBody BlockDto blockDto) {
        ApiResponse apiResponse = blockService.addBlock(blockDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Get
    @GetMapping
    public ResponseEntity<List<Block>> getBlock() {
        List<Block> blocks = blockService.getBlock();
        return ResponseEntity.ok(blocks);
    }

    //    Get by id
    @GetMapping("/{id}")
    public ResponseEntity<Block> getBlockById(@PathVariable Integer id) {
        Block blockById = blockService.getBlockById(id);
        return ResponseEntity.ok(blockById);
    }

    //    Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editBlock(@PathVariable Integer id, @Valid @RequestBody BlockDto blockDto) {
        ApiResponse apiResponse = blockService.editBlock(id, blockDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    //    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBlock(@PathVariable Integer id) {
        ApiResponse apiResponse = blockService.deleteBlock(id);
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

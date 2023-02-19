package com.example.codingbat.controller;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> upload(MultipartHttpServletRequest request){
        ApiResponse apiResponse = attachmentService.uploadFile(request);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }


    @GetMapping("/download/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFile(id, response);
    }
}

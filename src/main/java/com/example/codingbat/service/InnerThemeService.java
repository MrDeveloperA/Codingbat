package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Help;
import com.example.codingbat.entity.InnerTheme;
import com.example.codingbat.repository.HelpRepository;
import com.example.codingbat.repository.InnerThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InnerThemeService {
    @Autowired
    InnerThemeRepository innerThemeRepository;

    //      Create
    public ApiResponse addInnerTheme(InnerTheme innerTheme) {
        InnerTheme innerTheme1 = new InnerTheme();
        innerTheme1.setInnerTheme(innerTheme.getInnerTheme());
        innerTheme1.setCode(innerTheme.getCode());

        innerThemeRepository.save(innerTheme1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<InnerTheme> getInnerTheme() {
        return innerThemeRepository.findAll();
    }

    //    Get by id
    public InnerTheme getInnerThemeById(Integer id) {
        Optional<InnerTheme> optionalInnerTheme = innerThemeRepository.findById(id);
        if (!optionalInnerTheme.isPresent())
            return null;
        return optionalInnerTheme.get();
    }

    //    Update
    public ApiResponse editInnerTheme(Integer id, InnerTheme innerTheme) {
        Optional<InnerTheme> optionalInnerTheme = innerThemeRepository.findById(id);
        if (!optionalInnerTheme.isPresent())
            return new ApiResponse("Such InnerTheme was not found", false);
        InnerTheme editInnerTheme = optionalInnerTheme.get();
        editInnerTheme.setInnerTheme(innerTheme.getInnerTheme());
        editInnerTheme.setCode(innerTheme.getInnerTheme());

        innerThemeRepository.save(editInnerTheme);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteInnerTheme(Integer id) {
        try {
            innerThemeRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

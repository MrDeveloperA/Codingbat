package com.example.codingbat.service;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.OuterThemeDto;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.Link;
import com.example.codingbat.entity.OuterTheme;
import com.example.codingbat.entity.ThemeToPractice;
import com.example.codingbat.repository.AboutRepository;
import com.example.codingbat.repository.LinkRepository;
import com.example.codingbat.repository.OuterThemeRepository;
import com.example.codingbat.repository.ThemeToPracticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OuterThemeService {
    @Autowired
    OuterThemeRepository outerThemeRepository;
    @Autowired
    ThemeToPracticeRepository themeToPracticeRepository;


    //      Create
    public ApiResponse addThemeToPractice(OuterThemeDto outerThemeDto) {
        OuterTheme outerTheme = new OuterTheme();

        outerTheme.setOuterTheme(outerThemeDto.getOuterTheme());
        outerTheme.setNumberStar(outerThemeDto.getNumberStar());

        Optional<ThemeToPractice> optionalThemeToPractice = themeToPracticeRepository.findById(outerThemeDto.getThemeToPractice());
        if (!optionalThemeToPractice.isPresent())
            return new ApiResponse("Not found", false);
        outerTheme.setThemeToPractice(optionalThemeToPractice.get());

        outerTheme.setTick(outerThemeDto.isTick());

        outerThemeRepository.save(outerTheme);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<OuterTheme> getOuterTheme() {
        return outerThemeRepository.findAll();
    }

    //    Get by id
    public OuterTheme getOuterThemeById(Integer id) {
        Optional<OuterTheme> optionalOuterTheme = outerThemeRepository.findById(id);
        if (!optionalOuterTheme.isPresent())
            return null;
        return optionalOuterTheme.get();
    }

    //    Update
    public ApiResponse editOuterTheme(Integer id, OuterThemeDto outerThemeDto) {
        Optional<OuterTheme> optionalOuterTheme = outerThemeRepository.findById(id);
        if (!optionalOuterTheme.isPresent())
            return new ApiResponse("Not found", false);
        OuterTheme editOuterTheme = optionalOuterTheme.get();

        editOuterTheme.setOuterTheme(outerThemeDto.getOuterTheme());
        editOuterTheme.setNumberStar(outerThemeDto.getNumberStar());

        Optional<ThemeToPractice> optionalThemeToPractice = themeToPracticeRepository.findById(outerThemeDto.getThemeToPractice());
        if (!optionalThemeToPractice.isPresent())
            return new ApiResponse("Not found", false);
        editOuterTheme.setThemeToPractice(optionalThemeToPractice.get());

        editOuterTheme.setTick(outerThemeDto.isTick());

        outerThemeRepository.save(editOuterTheme);
        return new ApiResponse("Saved successfully", true);

    }

    //     Delete
    public ApiResponse deleteOuterTheme(Integer id) {
        try {
            outerThemeRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

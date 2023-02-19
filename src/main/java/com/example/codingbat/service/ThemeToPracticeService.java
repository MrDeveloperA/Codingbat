package com.example.codingbat.service;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.ThemeToPracticeDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeToPracticeService {
    @Autowired
    ThemeToPracticeRepository themeToPracticeRepository;
    @Autowired
    ProblemTextRepository problemTextRepository;
    @Autowired
    ConditionRepository conditionRepository;
    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    GivenCodeRepository givenCodeRepository;
    @Autowired
    EditCodeFromUserRepository editCodeFromUserRepository;


    //      Create
    public ApiResponse addThemePractice(ThemeToPracticeDto themeToPracticeDto) {
        ThemeToPractice themeToPractice = new ThemeToPractice();

        Optional<ProblemText> optionalProblemText = problemTextRepository.findById(themeToPracticeDto.getProblemText());
        if (!optionalProblemText.isPresent())
            return new ApiResponse("Not found", false);
        themeToPractice.setProblemText(optionalProblemText.get());

        Optional<Condition> optionalCondition = conditionRepository.findById(themeToPracticeDto.getCondition());
        if (!optionalCondition.isPresent())
            return new ApiResponse("Not found", false);
        themeToPractice.setCondition(optionalCondition.get());

        Optional<Solution> optionalSolution = solutionRepository.findById(themeToPracticeDto.getSolution());
        if (!optionalSolution.isPresent())
            return new ApiResponse("Not found", false);
        themeToPractice.setSolution(optionalSolution.get());

        Optional<GivenCode> optionalGivenCode = givenCodeRepository.findById(themeToPracticeDto.getGivenCode());
        if (!optionalGivenCode.isPresent())
            return new ApiResponse("Not found", false);
        themeToPractice.setGivenCode(optionalGivenCode.get());

        Optional<EditCodeFromUser> optionalEditCodeFromUser = editCodeFromUserRepository.findById(themeToPracticeDto.getEditCodeFromUser());
        if (!optionalEditCodeFromUser.isPresent())
            return new ApiResponse("Not found", false);
        themeToPractice.setEditCodeFromUser(optionalEditCodeFromUser.get());
        themeToPractice.setFontSize(themeToPracticeDto.getFontSize());
        themeToPractice.setShorterOutput(themeToPracticeDto.isShorterOutput());

        themeToPracticeRepository.save(themeToPractice);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<ThemeToPractice> getThemeToPractice() {
        return themeToPracticeRepository.findAll();
    }

    //    Get by id
    public ThemeToPractice getThemeToPracticeById(Integer id) {
        Optional<ThemeToPractice> optionalThemeToPractice = themeToPracticeRepository.findById(id);
        if (!optionalThemeToPractice.isPresent())
            return null;
        return optionalThemeToPractice.get();
    }

    //    Update
    public ApiResponse editThemeToPractice(Integer id, ThemeToPracticeDto themeToPracticeDto) {
        Optional<ThemeToPractice> optionalThemeToPractice = themeToPracticeRepository.findById(id);
        if (!optionalThemeToPractice.isPresent())
            return new ApiResponse("Such About was not found", false);
        ThemeToPractice editThemeToPractice = optionalThemeToPractice.get();

        Optional<ProblemText> optionalProblemText = problemTextRepository.findById(themeToPracticeDto.getProblemText());
        if (!optionalProblemText.isPresent())
            return new ApiResponse("Not found", false);
        editThemeToPractice.setProblemText(optionalProblemText.get());

        Optional<Condition> optionalCondition = conditionRepository.findById(themeToPracticeDto.getCondition());
        if (!optionalCondition.isPresent())
            return new ApiResponse("Not found", false);
        editThemeToPractice.setCondition(optionalCondition.get());

        Optional<Solution> optionalSolution = solutionRepository.findById(themeToPracticeDto.getSolution());
        if (!optionalSolution.isPresent())
            return new ApiResponse("Not found", false);
        editThemeToPractice.setSolution(optionalSolution.get());

        Optional<GivenCode> optionalGivenCode = givenCodeRepository.findById(themeToPracticeDto.getGivenCode());
        if (!optionalGivenCode.isPresent())
            return new ApiResponse("Not found", false);
        editThemeToPractice.setGivenCode(optionalGivenCode.get());

        Optional<EditCodeFromUser> optionalEditCodeFromUser = editCodeFromUserRepository.findById(themeToPracticeDto.getEditCodeFromUser());
        if (!optionalEditCodeFromUser.isPresent())
            return new ApiResponse("Not found", false);
        editThemeToPractice.setEditCodeFromUser(optionalEditCodeFromUser.get());
        editThemeToPractice.setFontSize(themeToPracticeDto.getFontSize());
        editThemeToPractice.setShorterOutput(themeToPracticeDto.isShorterOutput());

        themeToPracticeRepository.save(editThemeToPractice);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteThemeToPractice(Integer id) {
        try {
            themeToPracticeRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

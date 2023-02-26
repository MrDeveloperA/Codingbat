package com.example.codingbat.service;

import com.example.codingbat.diler.AnswerDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.ThemeDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    ThemeRepository themeRepository;
    @Autowired
    TaskTextRepository taskTextRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    GivenCodeRepository givenCodeRepository;
    @Autowired
    AnswerRepository answerRepository;


    //      Create
    public ApiResponse addTheme(ThemeDto themeDto) {
        Theme theme = new Theme();
        theme.setName(themeDto.getName());

        Optional<TaskText> optionalTaskText = taskTextRepository.findById(themeDto.getTaskText());
        if (!optionalTaskText.isPresent())
            return new ApiResponse("Not found", false);
        theme.setTaskText(optionalTaskText.get());

        Optional<Task> optionalTask = taskRepository.findById(themeDto.getTask());
        if (!optionalTask.isPresent())
            return new ApiResponse("Not found", false);
        theme.setTask(optionalTask.get());

        Optional<Solution> optionalSolution = solutionRepository.findById(themeDto.getSolution());
        if (!optionalSolution.isPresent())
            return new ApiResponse("Not found", false);
        theme.setSolution(optionalSolution.get());

        Optional<GivenCode> optionalGivenCode = givenCodeRepository.findById(themeDto.getGivenCode());
        if (!optionalGivenCode.isPresent())
            return new ApiResponse("Not found", false);
        theme.setGivenCode(optionalGivenCode.get());

        Optional<Answer> optionalAnswer = answerRepository.findById(themeDto.getAnswer());
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Not found", false);
        theme.setAnswer(optionalAnswer.get());

        themeRepository.save(theme);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Theme> getTheme() {
        return themeRepository.findAll();
    }

    //    Get by id
    public Theme getThemeById(Integer id) {
        Optional<Theme> optionalTheme = themeRepository.findById(id);
        if (!optionalTheme.isPresent())
            return null;
        return optionalTheme.get();
    }

    //    Update
    public ApiResponse editTheme(Integer id, ThemeDto themeDto) {
        Optional<Theme> optionalTheme = themeRepository.findById(id);
        if (!optionalTheme.isPresent())
            return new ApiResponse("Not found", false);
        Theme editTheme = optionalTheme.get();
        editTheme.setName(themeDto.getName());

        Optional<TaskText> optionalTaskText = taskTextRepository.findById(themeDto.getTaskText());
        if (!optionalTaskText.isPresent())
            return new ApiResponse("Not found", false);
        editTheme.setTaskText(optionalTaskText.get());

        Optional<Task> optionalTask = taskRepository.findById(themeDto.getTask());
        if (!optionalTask.isPresent())
            return new ApiResponse("Not found", false);
        editTheme.setTask(optionalTask.get());

        Optional<Solution> optionalSolution = solutionRepository.findById(themeDto.getSolution());
        if (!optionalSolution.isPresent())
            return new ApiResponse("Not found", false);
        editTheme.setSolution(optionalSolution.get());

        Optional<GivenCode> optionalGivenCode = givenCodeRepository.findById(themeDto.getGivenCode());
        if (!optionalGivenCode.isPresent())
            return new ApiResponse("Not found", false);
        editTheme.setGivenCode(optionalGivenCode.get());

        Optional<Answer> optionalAnswer = answerRepository.findById(themeDto.getAnswer());
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Not found", false);
        editTheme.setAnswer(optionalAnswer.get());

        themeRepository.save(editTheme);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteTheme(Integer id) {
        try {
            themeRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

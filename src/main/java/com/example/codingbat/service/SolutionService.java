package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.AnswerDto;
import com.example.codingbat.diler.SolutionDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {
    @Autowired
    SolutionRepository solutionRepository;
    @Autowired
    GivenCodeRepository givenCodeRepository;
    @Autowired
    RightAnswerRepository rightAnswerRepository;


    //      Create
    public ApiResponse addSolution(SolutionDto solutionDto) {
        Solution solution = new Solution();

        Optional<GivenCode> optionalGivenCode = givenCodeRepository.findById(solutionDto.getGivenCode());
        if (!optionalGivenCode.isPresent())
            return new ApiResponse("Not found", false);
        solution.setGivenCode(optionalGivenCode.get());

        Optional<RightAnswer> optionalRightAnswer = rightAnswerRepository.findById(solutionDto.getRightAnswer());
        if (!optionalRightAnswer.isPresent())
            return new ApiResponse("Not found", false);
        solution.setRightAnswer(optionalRightAnswer.get());

        solutionRepository.save(solution);
        return new ApiResponse("Saved successfully", true);

    }

    //  Get
    public List<Solution> getSolution() {
        return solutionRepository.findAll();
    }

    //    Get by id
    public Solution getSolutionById(Integer id) {
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (!optionalSolution.isPresent())
            return null;
        return optionalSolution.get();
    }

    //    Update
    public ApiResponse editSolution (Integer id, SolutionDto solutionDto) {
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (!optionalSolution.isPresent())
            return new ApiResponse("Not found", false);
        Solution editSolution = optionalSolution.get();

        Optional<GivenCode> optionalGivenCode = givenCodeRepository.findById(solutionDto.getGivenCode());
        if (!optionalGivenCode.isPresent())
            return new ApiResponse("Not found", false);
        editSolution.setGivenCode(optionalGivenCode.get());

        Optional<RightAnswer> optionalRightAnswer = rightAnswerRepository.findById(solutionDto.getRightAnswer());
        if (!optionalRightAnswer.isPresent())
            return new ApiResponse("Not found", false);
        editSolution.setRightAnswer(optionalRightAnswer.get());

        solutionRepository.save(editSolution);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteSolution(Integer id) {
        try {
            solutionRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

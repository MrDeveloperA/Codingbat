package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.Solution;
import com.example.codingbat.repository.DoneRepository;
import com.example.codingbat.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {
    @Autowired
    SolutionRepository solutionRepository;


    //      Create
    public ApiResponse addSolution(Solution solution) {
        Solution solution1 = new Solution();
        solution1.setText(solution.getText());

        solutionRepository.save(solution1);
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
    public ApiResponse editSolution(Integer id, Solution solution) {
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (!optionalSolution.isPresent())
            return new ApiResponse("Not found", false);
        Solution editSolution = optionalSolution.get();
        editSolution.setText(solution.getText());

        solutionRepository.save(editSolution);
        return new ApiResponse("Edited successfully", true);
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

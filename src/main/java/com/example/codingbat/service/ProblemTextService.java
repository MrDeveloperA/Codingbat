package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Privacy;
import com.example.codingbat.entity.ProblemText;
import com.example.codingbat.repository.PrivacyRepository;
import com.example.codingbat.repository.ProblemTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemTextService {
    @Autowired
    ProblemTextRepository problemTextRepository;


    //      Create
    public ApiResponse addProblemText(ProblemText problemText) {
        ProblemText problemText1 = new ProblemText();
        problemText1.setText(problemText.getText());

        problemTextRepository.save(problemText1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<ProblemText> getProblemText() {
        return problemTextRepository.findAll();
    }

    //    Get by id
    public ProblemText getProblemTextById(Integer id) {
        Optional<ProblemText> optionalProblemText = problemTextRepository.findById(id);
        if (!optionalProblemText.isPresent())
            return null;
        return optionalProblemText.get();
    }

    //    Update
    public ApiResponse editProblemText(Integer id, ProblemText problemText) {
        Optional<ProblemText> optionalProblemText = problemTextRepository.findById(id);
        if (!optionalProblemText.isPresent())
            return new ApiResponse("Not found", false);
        ProblemText editProblemText = optionalProblemText.get();
        editProblemText.setText(problemText.getText());

        problemTextRepository.save(editProblemText);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteProblemText(Integer id) {
        try {
            problemTextRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

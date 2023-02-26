package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.RightAnswer;
import com.example.codingbat.repository.RightAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RightAnswerService {
    @Autowired
    RightAnswerRepository rightAnswerRepository;

    //      Create
    public ApiResponse addRightAnswer(RightAnswer rightAnswer) {
        RightAnswer rightAnswer1 = new RightAnswer();
        rightAnswer1.setRightAnswer(rightAnswer.getRightAnswer());

        rightAnswerRepository.save(rightAnswer);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<RightAnswer> getRightAnswer() {
        return rightAnswerRepository.findAll();
    }

    //    Get by id
    public RightAnswer getRightAnswerById(Integer id) {
        Optional<RightAnswer> optionalRightAnswer = rightAnswerRepository.findById(id);
        if (!optionalRightAnswer.isPresent())
            return null;
        return optionalRightAnswer.get();
    }

    //    Update
    public ApiResponse editRightAnswer(Integer id, RightAnswer rightAnswer) {
        Optional<RightAnswer> optionalRightAnswer = rightAnswerRepository.findById(id);
        if (!optionalRightAnswer.isPresent())
            return new ApiResponse("Not found", false);

        RightAnswer editRightAnswer = optionalRightAnswer.get();
        editRightAnswer.setRightAnswer(rightAnswer.getRightAnswer());

        rightAnswerRepository.save(editRightAnswer);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteRightAnswer(Integer id) {
        try {
            rightAnswerRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

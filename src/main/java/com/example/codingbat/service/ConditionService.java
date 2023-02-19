package com.example.codingbat.service;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.Condition;
import com.example.codingbat.entity.Link;
import com.example.codingbat.repository.AboutRepository;
import com.example.codingbat.repository.ConditionRepository;
import com.example.codingbat.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConditionService {
    @Autowired
    ConditionRepository conditionRepository;


    //      Create
    public ApiResponse addCondition(Condition condition) {
        Condition condition1 = new Condition();
        condition1.setText(condition.getText());

        conditionRepository.save(condition1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Condition> getCondition() {
        return conditionRepository.findAll();
    }

    //    Get by id
    public Condition getConditionById(Integer id) {
        Optional<Condition> optionalCondition = conditionRepository.findById(id);
        if (!optionalCondition.isPresent())
            return null;
        return optionalCondition.get();
    }

    //    Update
    public ApiResponse editCondition(Integer id, Condition condition) {
        Optional<Condition> optionalCondition = conditionRepository.findById(id);
        if (!optionalCondition.isPresent())
            return new ApiResponse("Such Condition was not found", false);
        Condition editCondition = optionalCondition.get();
        editCondition.setText(condition.getText());

        conditionRepository.save(editCondition);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteCondition(Integer id) {
        try {
            conditionRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

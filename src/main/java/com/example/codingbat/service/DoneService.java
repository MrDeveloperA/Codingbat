package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Condition;
import com.example.codingbat.entity.Done;
import com.example.codingbat.repository.ConditionRepository;
import com.example.codingbat.repository.DoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoneService {
    @Autowired
    DoneRepository doneRepository;


    //      Create
    public ApiResponse addDone(Done done) {
        Done done1 = new Done();
        done1.setText(done.getText());

        doneRepository.save(done1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Done> getDone() {
        return doneRepository.findAll();
    }

    //    Get by id
    public Done getDoneById(Integer id) {
        Optional<Done> optionalDone = doneRepository.findById(id);
        if (!optionalDone.isPresent())
            return null;
        return optionalDone.get();
    }

    //    Update
    public ApiResponse editDone(Integer id, Done done) {
        Optional<Done> optionalDone = doneRepository.findById(id);
        if (!optionalDone.isPresent())
            return new ApiResponse("Such Done was not found", false);
        Done editDone = optionalDone.get();
        editDone.setText(done.getText());

        doneRepository.save(editDone);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteDone(Integer id) {
        try {
            doneRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

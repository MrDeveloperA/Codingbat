package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Declare;
import com.example.codingbat.repository.DeclareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeclareService {
    @Autowired
    DeclareRepository declareRepository;


    //      Create
    public ApiResponse addDeclare(Declare declare) {
        Declare declare1 = new Declare();
        declare1.setText(declare.getText());

        declareRepository.save(declare1);
        return new ApiResponse("Saved successfully", true);

    }

    //  Get
    public List<Declare> getDeclare() {
        return declareRepository.findAll();
    }

    //    Get by id
    public Declare getDeclareById(Integer id) {
        Optional<Declare> optionalDeclare = declareRepository.findById(id);
        if (!optionalDeclare.isPresent())
            return null;
        return optionalDeclare.get();
    }

    //    Update
    public ApiResponse editDeclare(Integer id, Declare declare) {
        Optional<Declare> optionalDeclare = declareRepository.findById(id);
        if (!optionalDeclare.isPresent())
            return new ApiResponse("Not found", false);

        Declare editDeclare = optionalDeclare.get();
        editDeclare.setText(declare.getText());

        declareRepository.save(editDeclare);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteDeclare(Integer id) {
        try {
            declareRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

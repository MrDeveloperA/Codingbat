package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.GivenCode;
import com.example.codingbat.repository.DoneRepository;
import com.example.codingbat.repository.GivenCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GivenCodeService {
    @Autowired
    GivenCodeRepository givenCodeRepository;


    //      Create
    public ApiResponse addGivenCode(GivenCode givenCode) {
        GivenCode givenCode1 = new GivenCode();
        givenCode1.setText(givenCode.getText());

        givenCodeRepository.save(givenCode1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<GivenCode> getGivenCode() {
        return givenCodeRepository.findAll();
    }

    //    Get by id
    public GivenCode getGivenCodeById(Integer id) {
        Optional<GivenCode> optionalGivenCode = givenCodeRepository.findById(id);
        if (!optionalGivenCode.isPresent())
            return null;
        return optionalGivenCode.get();
    }

    //    Update
    public ApiResponse editGivenCode(Integer id, GivenCode givenCode) {
        Optional<GivenCode> optionalGivenCode = givenCodeRepository.findById(id);
        if (!optionalGivenCode.isPresent())
            return new ApiResponse("Such GivenCode was not found", false);
        GivenCode editGivenCode = optionalGivenCode.get();
        editGivenCode.setText(givenCode.getText());

        givenCodeRepository.save(editGivenCode);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteGivenCode(Integer id) {
        try {
            givenCodeRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.Privacy;
import com.example.codingbat.repository.DoneRepository;
import com.example.codingbat.repository.PrivacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrivacyService {
    @Autowired
    PrivacyRepository privacyRepository;


    //      Create
    public ApiResponse addPrivacy(Privacy privacy) {
        Privacy privacy1 = new Privacy();
        privacy1.setText(privacy.getText());

        privacyRepository.save(privacy1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Privacy> getPrivacy() {
        return privacyRepository.findAll();
    }

    //    Get by id
    public Privacy getPrivacyById(Integer id) {
        Optional<Privacy> optionalPrivacy = privacyRepository.findById(id);
        if (!optionalPrivacy.isPresent())
            return null;
        return optionalPrivacy.get();
    }

    //    Update
    public ApiResponse editPrivacy(Integer id, Privacy privacy) {
        Optional<Privacy> optionalPrivacy = privacyRepository.findById(id);
        if (!optionalPrivacy.isPresent())
            return new ApiResponse("Not found", false);
        Privacy editPrivacy = optionalPrivacy.get();
        editPrivacy.setText(privacy.getText());

        privacyRepository.save(editPrivacy);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deletePrivacy(Integer id) {
        try {
            privacyRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

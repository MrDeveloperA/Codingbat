package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Description;
import com.example.codingbat.repository.DescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DescriptionService {
    @Autowired
    DescriptionRepository descriptionRepository;

    //      Create
    public ApiResponse addDescription(Description description) {
        Description description1 = new Description();
        description1.setText(description1.getText());

        descriptionRepository.save(description);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Description> getDescription() {
        return descriptionRepository.findAll();
    }

    //    Get by id
    public Description getDescriptionById(Integer id) {
        Optional<Description> optionalDescription = descriptionRepository.findById(id);
        if (!optionalDescription.isPresent())
            return null;
        return optionalDescription.get();
    }

    //    Update
    public ApiResponse editDescription(Integer id, Description description) {
        Optional<Description> optionalDescription = descriptionRepository.findById(id);
        if (!optionalDescription.isPresent())
            return new ApiResponse("Not found", false);

        Description editDescription = optionalDescription.get();
        editDescription.setText(description.getText());

        descriptionRepository.save(editDescription);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteDescription(Integer id) {
        try {
            descriptionRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

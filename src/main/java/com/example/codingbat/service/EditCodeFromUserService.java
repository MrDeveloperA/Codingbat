package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.EditCodeFromUser;
import com.example.codingbat.repository.DoneRepository;
import com.example.codingbat.repository.EditCodeFromUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditCodeFromUserService {
    @Autowired
    EditCodeFromUserRepository editCodeFromUserRepository;


    //      Create
    public ApiResponse addEditCode(EditCodeFromUser editCodeFromUser) {
        EditCodeFromUser editCodeFromUser1 = new EditCodeFromUser();
        editCodeFromUser1.setText(editCodeFromUser1.getText());

        editCodeFromUserRepository.save(editCodeFromUser);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<EditCodeFromUser> getEditCodeFromUser() {
        return editCodeFromUserRepository.findAll();
    }

    //    Get by id
    public EditCodeFromUser getEditCodeFromUserById(Integer id) {
        Optional<EditCodeFromUser> optionalEditCodeFromUser = editCodeFromUserRepository.findById(id);
        if (!optionalEditCodeFromUser.isPresent())
            return null;
        return optionalEditCodeFromUser.get();
    }

    //    Update
    public ApiResponse editEditCodeFromUser(Integer id, EditCodeFromUser editCodeFromUser) {
        Optional<EditCodeFromUser> optionalEditCodeFromUser = editCodeFromUserRepository.findById(id);
        if (!optionalEditCodeFromUser.isPresent())
            return new ApiResponse("Such code from user was not found", false);
        EditCodeFromUser updateCodeFromUser = optionalEditCodeFromUser.get();
        updateCodeFromUser.setText(editCodeFromUser.getText());

        editCodeFromUserRepository.save(updateCodeFromUser);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteEditCodeFromUser(Integer id) {
        try {
            editCodeFromUserRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

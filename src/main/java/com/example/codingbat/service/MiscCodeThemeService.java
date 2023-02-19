package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.MiscCodePractice;
import com.example.codingbat.entity.MiscCodeTheme;
import com.example.codingbat.repository.DoneRepository;
import com.example.codingbat.repository.MiscCodeThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiscCodeThemeService {
    @Autowired
    MiscCodeThemeRepository miscCodeThemeRepository;


    //      Create
    public ApiResponse addMiscCodeTheme(MiscCodeTheme miscCodeTheme) {
        MiscCodeTheme miscCodeTheme1 = new MiscCodeTheme();
        miscCodeTheme1.setText(miscCodeTheme.getText());

        miscCodeThemeRepository.save(miscCodeTheme1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<MiscCodeTheme> getMiscCodeTheme() {
        return miscCodeThemeRepository.findAll();
    }

    //    Get by id
    public MiscCodeTheme getMiscCodeThemeById(Integer id) {
        Optional<MiscCodeTheme> optionalMiscCodeTheme = miscCodeThemeRepository.findById(id);
        if (!optionalMiscCodeTheme.isPresent())
            return null;
        return optionalMiscCodeTheme.get();
    }

    //    Update
    public ApiResponse editMiscCodeTheme(Integer id, MiscCodeTheme miscCodeTheme) {
        Optional<MiscCodeTheme> optionalMiscCodeTheme = miscCodeThemeRepository.findById(id);
        if (!optionalMiscCodeTheme.isPresent())
            return new ApiResponse("Not found", false);
        MiscCodeTheme editMiscCodeTheme = optionalMiscCodeTheme.get();
        editMiscCodeTheme.setText(miscCodeTheme.getText());

        miscCodeThemeRepository.save(editMiscCodeTheme);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteMiscCodeTheme(Integer id) {
        try {
            miscCodeThemeRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

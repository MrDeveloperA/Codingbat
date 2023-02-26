package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.LearnThemeDto;
import com.example.codingbat.entity.Attachment;
import com.example.codingbat.entity.Description;
import com.example.codingbat.entity.LearnThemes;
import com.example.codingbat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearnThemeService {
    @Autowired
    LearnThemeRepository learnThemeRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    DescriptionRepository descriptionRepository;

    //      Create
    public ApiResponse addLearnTheme(LearnThemeDto learnThemeDto) {
        LearnThemes learnThemes = new LearnThemes();
        learnThemes.setName(learnThemeDto.getName());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(learnThemeDto.getAttachment());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Not found", false);
        learnThemes.setAttachment(optionalAttachment.get());

        Optional<Description> optionalDescription = descriptionRepository.findById(learnThemeDto.getDescription());
        if (!optionalDescription.isPresent())
            return new ApiResponse("Not found", false);
        learnThemes.setDescription(optionalDescription.get());

        learnThemeRepository.save(learnThemes);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<LearnThemes> getLearnThemes() {
        return learnThemeRepository.findAll();
    }

    //    Get by id
    public LearnThemes getLearnThemeById(Integer id) {
        Optional<LearnThemes> optionalLearnThemes = learnThemeRepository.findById(id);
        if (!optionalLearnThemes.isPresent())
            return null;
        return optionalLearnThemes.get();
    }

    //    Update
    public ApiResponse editLearnThemes(Integer id, LearnThemeDto learnThemeDto) {
        Optional<LearnThemes> optionalLearnThemes = learnThemeRepository.findById(id);
        if (!optionalLearnThemes.isPresent())
            return new ApiResponse("Not found", false);

        LearnThemes editLearnThemes = optionalLearnThemes.get();

        editLearnThemes.setName(learnThemeDto.getName());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(learnThemeDto.getAttachment());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Not found", false);
        editLearnThemes.setAttachment(optionalAttachment.get());

        Optional<Description> optionalDescription = descriptionRepository.findById(learnThemeDto.getDescription());
        if (!optionalDescription.isPresent())
            return new ApiResponse("Not found", false);
        editLearnThemes.setDescription(optionalDescription.get());

        learnThemeRepository.save(editLearnThemes);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteLearnTheme(Integer id) {
        try {
            learnThemeRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

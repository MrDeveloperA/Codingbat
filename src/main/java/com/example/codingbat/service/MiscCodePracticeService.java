package com.example.codingbat.service;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.MiscCodePracticeDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiscCodePracticeService {
    @Autowired
    MiscCodePracticeRepository miscCodePracticeRepository;
    @Autowired
    MiscCodeThemeRepository miscCodeThemeRepository;
    @Autowired
    AttachmentRepository attachmentRepository;


    //      Create
    public ApiResponse addMiscCodePractice(MiscCodePracticeDto miscCodePracticeDto) {
        MiscCodePractice miscCodePractice = new MiscCodePractice();

        Optional<MiscCodeTheme> optionalMiscCodeTheme = miscCodeThemeRepository.findById(miscCodePracticeDto.getMiscCodeTheme());
        if (!optionalMiscCodeTheme.isPresent())
            return new ApiResponse("Not found", false);
        miscCodePractice.setMiscCodeTheme(optionalMiscCodeTheme.get());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(miscCodePracticeDto.getAttachment());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Not found", false);
        miscCodePractice.setAttachment(optionalAttachment.get());


        miscCodePracticeRepository.save(miscCodePractice);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<MiscCodePractice> getMiscCodePractice() {
        return miscCodePracticeRepository.findAll();
    }

    //    Get by id
    public MiscCodePractice getMiscCodePracticeById(Integer id) {
        Optional<MiscCodePractice> optionalMiscCodePractice = miscCodePracticeRepository.findById(id);
        if (!optionalMiscCodePractice.isPresent())
            return null;
        return optionalMiscCodePractice.get();
    }

    //    Update
    public ApiResponse editMiscCodePractice(Integer id, MiscCodePracticeDto miscCodePracticeDto) {
        Optional<MiscCodePractice> optionalMiscCodePractice = miscCodePracticeRepository.findById(id);
        if (!optionalMiscCodePractice.isPresent())
            return new ApiResponse("Not found", false);
        MiscCodePractice editMiscCodePractice = optionalMiscCodePractice.get();


        Optional<MiscCodeTheme> optionalMiscCodeTheme = miscCodeThemeRepository.findById(miscCodePracticeDto.getMiscCodeTheme());
        if (!optionalMiscCodeTheme.isPresent())
            return new ApiResponse("Not found", false);
        editMiscCodePractice.setMiscCodeTheme(optionalMiscCodeTheme.get());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(miscCodePracticeDto.getAttachment());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Not found", false);
        editMiscCodePractice.setAttachment(optionalAttachment.get());


        miscCodePracticeRepository.save(editMiscCodePractice);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteMiscCodePractice(Integer id) {
        try {
            miscCodePracticeRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

package com.example.codingbat.service;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.CodeHelpVideoDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeHelpVideoService {
    @Autowired
    CodeHelpVideoRepository codeHelpVideoRepository;
    @Autowired
    InnerThemeRepository innerThemeRepository;
    @Autowired
    InnerThemeWithVideoRepository innerThemeWithVideoRepository;


    //      Create
    public ApiResponse addCodeHelpVideo(CodeHelpVideoDto codeHelpVideoDto) {

        Optional<InnerTheme> optionalInnerTheme = innerThemeRepository.findById(codeHelpVideoDto.getInnerTheme());
        if (!optionalInnerTheme.isPresent())
            return new ApiResponse("Such inner theme was not found", false);

        Optional<InnerThemeWithVideo> optionalInnerThemeWithVideo = innerThemeWithVideoRepository.findById(codeHelpVideoDto.getInnerThemeWithVideo());
        if (!optionalInnerThemeWithVideo.isPresent())
            return new ApiResponse("Such video was not found", false);

        CodeHelpVideo codeHelpVideo = new CodeHelpVideo();
        codeHelpVideo.setInnerTheme(optionalInnerTheme.get());
        codeHelpVideo.setInnerThemeWithVideo(optionalInnerThemeWithVideo.get());

        codeHelpVideoRepository.save(codeHelpVideo);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<CodeHelpVideo> getCodeHelpVideo() {
        return codeHelpVideoRepository.findAll();
    }

    //    Get by id
    public CodeHelpVideo getCodeHelpVideoById(Integer id) {
        Optional<CodeHelpVideo> optionalCodeHelpVideo = codeHelpVideoRepository.findById(id);
        if (!optionalCodeHelpVideo.isPresent())
            return null;
        return optionalCodeHelpVideo.get();
    }

    //    Update
    public ApiResponse editCodeHelpVideo(Integer id, CodeHelpVideoDto codeHelpVideoDto) {
        Optional<CodeHelpVideo> optionalCodeHelpVideo = codeHelpVideoRepository.findById(id);
        if (!optionalCodeHelpVideo.isPresent())
            return new ApiResponse("Such Code Help video was not found", false);
        CodeHelpVideo editCodeHelpVideo = optionalCodeHelpVideo.get();

        Optional<InnerTheme> optionalInnerTheme = innerThemeRepository.findById(codeHelpVideoDto.getInnerTheme());
        if (!optionalInnerTheme.isPresent())
            return new ApiResponse("Such inner theme was not found", false);

        Optional<InnerThemeWithVideo> optionalInnerThemeWithVideo = innerThemeWithVideoRepository.findById(codeHelpVideoDto.getInnerThemeWithVideo());
        if (!optionalInnerThemeWithVideo.isPresent())
            return new ApiResponse("Such video was not found", false);

        editCodeHelpVideo.setInnerTheme(optionalInnerTheme.get());
        editCodeHelpVideo.setInnerThemeWithVideo(optionalInnerThemeWithVideo.get());

        codeHelpVideoRepository.save(editCodeHelpVideo);
        return new ApiResponse("Edited successfully", true);

    }

    //     Delete
    public ApiResponse deleteCodeHelpVideo(Integer id) {
        try {
            codeHelpVideoRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

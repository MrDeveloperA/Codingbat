package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.InnerThemeWithVideoDto;
import com.example.codingbat.entity.Attachment;
import com.example.codingbat.entity.InnerTheme;
import com.example.codingbat.entity.InnerThemeWithVideo;
import com.example.codingbat.repository.AttachmentRepository;
import com.example.codingbat.repository.InnerThemeRepository;
import com.example.codingbat.repository.InnerThemeWithVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InnerThemeWithVideoService {
    @Autowired
    InnerThemeWithVideoRepository innerThemeWithVideoRepository;
    @Autowired
    AttachmentRepository attachmentRepository;

    //      Create
    public ApiResponse addInnerThemeWithVideo(InnerThemeWithVideoDto innerThemeWithVideoDto) {
        InnerThemeWithVideo innerThemeWithVideo = new InnerThemeWithVideo();
        innerThemeWithVideo.setInnerThemeWithVideo(innerThemeWithVideoDto.getInnerThemeWithVideo());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(innerThemeWithVideoDto.getAttachment());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Such attachment was not found", true);
        innerThemeWithVideo.setAttachment(optionalAttachment.get());

        innerThemeWithVideoRepository.save(innerThemeWithVideo);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<InnerThemeWithVideo> getInnerThemeWithVideo() {
        return innerThemeWithVideoRepository.findAll();
    }

    //    Get by id
    public InnerThemeWithVideo getInnerThemeWithVideoById(Integer id) {
        Optional<InnerThemeWithVideo> optionalInnerThemeWithVideo = innerThemeWithVideoRepository.findById(id);
        if (!optionalInnerThemeWithVideo.isPresent())
            return null;
        return optionalInnerThemeWithVideo.get();
    }

    //    Update
    public ApiResponse editInnerThemeWithVideo(Integer id, InnerThemeWithVideoDto innerThemeWithVideoDto) {
        Optional<InnerThemeWithVideo> optionalInnerThemeWithVideo = innerThemeWithVideoRepository.findById(id);
        if (!optionalInnerThemeWithVideo.isPresent())
            return new ApiResponse("Such InnerThemeWithVideo was not found", false);
        InnerThemeWithVideo editInnerThemeWithVideo = optionalInnerThemeWithVideo.get();
        editInnerThemeWithVideo.setInnerThemeWithVideo(innerThemeWithVideoDto.getInnerThemeWithVideo());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(innerThemeWithVideoDto.getAttachment());
        if (!optionalAttachment.isPresent())
            return new ApiResponse("Such attachment was not found", true);
        editInnerThemeWithVideo.setAttachment(optionalAttachment.get());

        innerThemeWithVideoRepository.save(editInnerThemeWithVideo);
        return new ApiResponse("Saved successfully", true);    }

    //     Delete
    public ApiResponse deleteInnerThemeWithVideo(Integer id) {
        try {
            innerThemeWithVideoRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

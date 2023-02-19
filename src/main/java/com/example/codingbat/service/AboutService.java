package com.example.codingbat.service;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.About;
import com.example.codingbat.entity.Link;
import com.example.codingbat.repository.AboutRepository;
import com.example.codingbat.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AboutService {
    @Autowired
    AboutRepository aboutRepository;
    @Autowired
    LinkRepository linkRepository;


    //      Create
    public ApiResponse addAbout(AboutDto aboutDto) {
        About about = new About();
        about.setAboutText(aboutDto.getAboutText());

        Optional<Link> optionalLink = linkRepository.findById(aboutDto.getLink());
        if (!optionalLink.isPresent())
            return new ApiResponse("Such link was not found", false);
        about.setLink(optionalLink.get());
        aboutRepository.save(about);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<About> getAbout() {
        return aboutRepository.findAll();
    }

    //    Get by id
    public About getAboutById(Integer id) {
        Optional<About> optionalAbout = aboutRepository.findById(id);
        if (!optionalAbout.isPresent())
            return null;
        return optionalAbout.get();
    }

    //    Update
    public ApiResponse editAbout(Integer id, AboutDto aboutDto) {
        Optional<About> optionalAbout = aboutRepository.findById(id);
        if (!optionalAbout.isPresent())
            return new ApiResponse("Such About was not found", false);
        About editAbout = optionalAbout.get();
        editAbout.setAboutText(aboutDto.getAboutText());

        Optional<Link> optionalLink = linkRepository.findById(aboutDto.getLink());
        if (!optionalLink.isPresent())
            return new ApiResponse("Such link was not found", false);
        editAbout.setLink(optionalLink.get());

        aboutRepository.save(editAbout);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteAbout(Integer id) {
        try {
            aboutRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

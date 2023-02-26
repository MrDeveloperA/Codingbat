package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.UserDto;
import com.example.codingbat.diler.WebSiteDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebSiteService {
    @Autowired
    WebSiteRepository webSiteRepository;
    @Autowired
    LearnThemeRepository learnThemeRepository;
    @Autowired
    AccountRepository accountRepository;

    //      Create
    public ApiResponse addWebSite(WebSiteDto webSiteDto) {
        WebSite webSite = new WebSite();
        webSite.setName(webSiteDto.getName());

        Optional<LearnThemes> optionalLearnThemes = learnThemeRepository.findById(webSiteDto.getLearnThemes());
        if (!optionalLearnThemes.isPresent())
            return new ApiResponse("Not found", false);
        webSite.setLearnThemes(optionalLearnThemes.get());

        Optional<Account> optionalAccount = accountRepository.findById(webSiteDto.getAccount());
        if (!optionalAccount.isPresent())
            return new ApiResponse("Not found", false);
        webSite.setAccount(optionalAccount.get());

        webSiteRepository.save(webSite);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<WebSite> getWebSite() {
        return webSiteRepository.findAll();
    }

    //    Get by id
    public WebSite getWebSiteById(Integer id) {
        Optional<WebSite> optionalWebSite = webSiteRepository.findById(id);
        if (!optionalWebSite.isPresent())
            return null;
        return optionalWebSite.get();
    }

    //    Update
    public ApiResponse editWebSite(Integer id, WebSiteDto webSiteDto) {
        Optional<WebSite> optionalWebSite = webSiteRepository.findById(id);
        if (!optionalWebSite.isPresent())
            return new ApiResponse("Not found", false);
        WebSite editWebSite = optionalWebSite.get();

        editWebSite.setName(webSiteDto.getName());

        Optional<LearnThemes> optionalLearnThemes = learnThemeRepository.findById(webSiteDto.getLearnThemes());
        if (!optionalLearnThemes.isPresent())
            return new ApiResponse("Not found", false);
        editWebSite.setLearnThemes(optionalLearnThemes.get());

        Optional<Account> optionalAccount = accountRepository.findById(webSiteDto.getAccount());
        if (!optionalAccount.isPresent())
            return new ApiResponse("Not found", false);
        editWebSite.setAccount(optionalAccount.get());

        webSiteRepository.save(editWebSite);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteWebSite(Integer id) {
        try {
            webSiteRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

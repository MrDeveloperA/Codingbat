package com.example.codingbat.service;

import com.example.codingbat.diler.AboutDto;
import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.MainPageDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainPageService {
    @Autowired
    MainPageRepository mainPageRepository;
    @Autowired
    HelpRepository helpRepository;
    @Autowired
    AboutRepository aboutRepository;
    @Autowired
    CodeHelpVideoRepository codeHelpVideoRepository;
    @Autowired
    DoneRepository doneRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;
    @Autowired
    OuterThemeRepository outerThemeRepository;
    @Autowired
    MiscCodePracticeRepository miscCodePracticeRepository;
    @Autowired
    PrivacyRepository privacyRepository;

    //      Create
    public ApiResponse addMainPage(MainPageDto mainPageDto) {
        MainPage mainPage = new MainPage();

        mainPage.setNameSite(mainPageDto.getNameSite());
        mainPage.setNotification(mainPageDto.getNotification());

        Optional<Help> optionalHelp = helpRepository.findById(mainPageDto.getHelp());
        if (!optionalHelp.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setHelp(optionalHelp.get());

        Optional<About> optionalAbout = aboutRepository.findById(mainPageDto.getAbout());
        if (!optionalAbout.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setAbout(optionalAbout.get());

        Optional<CodeHelpVideo> optionalCodeHelpVideo = codeHelpVideoRepository.findById(mainPageDto.getCodeHelpVideo());
        if (!optionalCodeHelpVideo.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setCodeHelpVideo(optionalCodeHelpVideo.get());


        Optional<Done> optionalDone = doneRepository.findById(mainPageDto.getDone());
        if (!optionalDone.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setDone(optionalDone.get());

        Optional<Account> optionalAccount = accountRepository.findById(mainPageDto.getAccount());
        if (!optionalAccount.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setAccount(optionalAccount.get());

        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(mainPageDto.getProgrammingLanguage());
        if (!optionalProgrammingLanguage.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setProgrammingLanguage(optionalProgrammingLanguage.get());

        Optional<OuterTheme> optionalOuterTheme = outerThemeRepository.findById(mainPageDto.getOuterTheme());
        if (!optionalOuterTheme.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setOuterTheme(optionalOuterTheme.get());

        Optional<MiscCodePractice> optionalMiscCodePractice = miscCodePracticeRepository.findById(mainPageDto.getMiscCodePractice());
        if (!optionalMiscCodePractice.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setMiscCodePractice(optionalMiscCodePractice.get());

        Optional<Privacy> optionalPrivacy = privacyRepository.findById(mainPageDto.getPrivacy());
        if (!optionalPrivacy.isPresent())
            return new ApiResponse("Not found", false);
        mainPage.setPrivacy(optionalPrivacy.get());

        mainPageRepository.save(mainPage);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<MainPage> getMainPage() {
        return mainPageRepository.findAll();
    }

    //    Get by id
    public MainPage getMainPageById(Integer id) {
        Optional<MainPage> optionalMainPage = mainPageRepository.findById(id);
        if (!optionalMainPage.isPresent())
            return null;
        return optionalMainPage.get();
    }

    //    Update
    public ApiResponse editMainPage(Integer id, MainPageDto mainPageDto) {
        Optional<MainPage> optionalMainPage = mainPageRepository.findById(id);
        if (!optionalMainPage.isPresent())
            return new ApiResponse("Not found", false);
        MainPage editMainPage = optionalMainPage.get();

        editMainPage.setNameSite(mainPageDto.getNameSite());
        editMainPage.setNotification(mainPageDto.getNotification());

        Optional<Help> optionalHelp = helpRepository.findById(mainPageDto.getHelp());
        if (!optionalHelp.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setHelp(optionalHelp.get());

        Optional<About> optionalAbout = aboutRepository.findById(mainPageDto.getAbout());
        if (!optionalAbout.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setAbout(optionalAbout.get());

        Optional<CodeHelpVideo> optionalCodeHelpVideo = codeHelpVideoRepository.findById(mainPageDto.getCodeHelpVideo());
        if (!optionalCodeHelpVideo.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setCodeHelpVideo(optionalCodeHelpVideo.get());


        Optional<Done> optionalDone = doneRepository.findById(mainPageDto.getDone());
        if (!optionalDone.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setDone(optionalDone.get());

        Optional<Account> optionalAccount = accountRepository.findById(mainPageDto.getAccount());
        if (!optionalAccount.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setAccount(optionalAccount.get());

        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(mainPageDto.getProgrammingLanguage());
        if (!optionalProgrammingLanguage.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setProgrammingLanguage(optionalProgrammingLanguage.get());

        Optional<OuterTheme> optionalOuterTheme = outerThemeRepository.findById(mainPageDto.getOuterTheme());
        if (!optionalOuterTheme.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setOuterTheme(optionalOuterTheme.get());

        Optional<MiscCodePractice> optionalMiscCodePractice = miscCodePracticeRepository.findById(mainPageDto.getMiscCodePractice());
        if (!optionalMiscCodePractice.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setMiscCodePractice(optionalMiscCodePractice.get());

        Optional<Privacy> optionalPrivacy = privacyRepository.findById(mainPageDto.getPrivacy());
        if (!optionalPrivacy.isPresent())
            return new ApiResponse("Not found", false);
        editMainPage.setPrivacy(optionalPrivacy.get());

        mainPageRepository.save(editMainPage);
        return new ApiResponse("Edited successfully", true);

    }

    //     Delete
    public ApiResponse deleteMain(Integer id) {
        try {
            mainPageRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

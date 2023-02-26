package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.LanguageDto;
import com.example.codingbat.entity.Block;
import com.example.codingbat.entity.Language;
import com.example.codingbat.repository.BlockRepository;
import com.example.codingbat.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    BlockRepository blockRepository;


    //      Create
    public ApiResponse addLanguage(LanguageDto languageDto) {
        Language language = new Language();
        language.setName(languageDto.getName());

        Optional<Block> optionalBlock = blockRepository.findById(languageDto.getBlock());
        if (!optionalBlock.isPresent())
            return new ApiResponse("Not found", false);
        language.setBlock(optionalBlock.get());
        languageRepository.save(language);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Language> getLanguage() {
        return languageRepository.findAll();
    }

    //    Get by id
    public Language getLanguageById(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return null;
        return optionalLanguage.get();
    }

    //    Update
    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Not found", false);
        Language editLanguage = optionalLanguage.get();

        Optional<Block> optionalBlock = blockRepository.findById(languageDto.getBlock());
        if (!optionalBlock.isPresent())
            return new ApiResponse("Not found", false);
        editLanguage.setBlock(optionalBlock.get());
        languageRepository.save(editLanguage);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteLanguage(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.ProblemText;
import com.example.codingbat.entity.ProgrammingLanguage;
import com.example.codingbat.repository.ProblemTextRepository;
import com.example.codingbat.repository.ProgrammingLanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageService {
    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;

    //      Create
    public ApiResponse addPrLanguageText(ProgrammingLanguage programmingLanguage) {
        ProgrammingLanguage programmingLanguage1 = new ProgrammingLanguage();
        programmingLanguage1.setLanguage(programmingLanguage.getLanguage());

        programmingLanguageRepository.save(programmingLanguage1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<ProgrammingLanguage> getProgrammingLanguage() {
        return programmingLanguageRepository.findAll();
    }

    //    Get by id
    public ProgrammingLanguage getPrLanguageTextById(Integer id) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
        if (!optionalProgrammingLanguage.isPresent())
            return null;
        return optionalProgrammingLanguage.get();
    }

    //    Update
    public ApiResponse editPrLanguage(Integer id, ProgrammingLanguage programmingLanguage) {
        Optional<ProgrammingLanguage> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
        if (!optionalProgrammingLanguage.isPresent())
            return new ApiResponse("Not found", false);
        ProgrammingLanguage editProgrammingLanguage = optionalProgrammingLanguage.get();
        editProgrammingLanguage.setLanguage(programmingLanguage.getLanguage());

        programmingLanguageRepository.save(editProgrammingLanguage);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deletePrLanguage(Integer id) {
        try {
            programmingLanguageRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

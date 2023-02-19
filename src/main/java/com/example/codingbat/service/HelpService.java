package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Done;
import com.example.codingbat.entity.Help;
import com.example.codingbat.repository.DoneRepository;
import com.example.codingbat.repository.HelpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HelpService {
    @Autowired
    HelpRepository helpRepository;


    //      Create
    public ApiResponse addHelp(Help help) {
        Help help1 = new Help();
        help1.setHelpText(help.getHelpText());
        help1.setCodeBadges(help.getCodeBadges());

        helpRepository.save(help1);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Help> getHelp() {
        return helpRepository.findAll();
    }

    //    Get by id
    public Help getHelpById(Integer id) {
        Optional<Help> optionalHelp = helpRepository.findById(id);
        if (!optionalHelp.isPresent())
            return null;
        return optionalHelp.get();
    }

    //    Update
    public ApiResponse editHelp(Integer id, Help help) {
        Optional<Help> optionalHelp = helpRepository.findById(id);
        if (!optionalHelp.isPresent())
            return new ApiResponse("Such Help was not found", false);
        Help editHelp = optionalHelp.get();
        editHelp.setHelpText(help.getHelpText());
        editHelp.setCodeBadges(help.getCodeBadges());

        helpRepository.save(editHelp);
        return new ApiResponse("Edited successfully", true);
    }

    //     Delete
    public ApiResponse deleteHelp(Integer id) {
        try {
            helpRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

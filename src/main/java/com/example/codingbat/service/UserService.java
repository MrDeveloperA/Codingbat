package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.ThemeDto;
import com.example.codingbat.diler.UserDto;
import com.example.codingbat.entity.*;
import com.example.codingbat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    LanguageRepository languageRepository;

    //      Create
    public ApiResponse addUser(UserDto userDto) {
        User user = new User();

        Optional<Account> optionalAccount = accountRepository.findById(userDto.getAccount());
        if (!optionalAccount.isPresent())
            return new ApiResponse("Not found", false);
        user.setAccount(optionalAccount.get());

        Optional<Answer> optionalAnswer = answerRepository.findById(userDto.getAnswer());
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Not found", false);
        user.setAnswer(optionalAnswer.get());

        Optional<Language> optionalLanguage = languageRepository.findById(userDto.getLanguage());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Not found", false);
        user.setLanguage(optionalLanguage.get());

        userRepository.save(user);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<User> getUser() {
        return userRepository.findAll();
    }

    //    Get by id
    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return null;
        return optionalUser.get();
    }

    //    Update
    public ApiResponse editUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("Not found", false);
        User editUser = optionalUser.get();

        Optional<Account> optionalAccount = accountRepository.findById(userDto.getAccount());
        if (!optionalAccount.isPresent())
            return new ApiResponse("Not found", false);
        editUser.setAccount(optionalAccount.get());

        Optional<Answer> optionalAnswer = answerRepository.findById(userDto.getAnswer());
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Not found", false);
        editUser.setAnswer(optionalAnswer.get());

        Optional<Language> optionalLanguage = languageRepository.findById(userDto.getLanguage());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Not found", false);
        editUser.setLanguage(optionalLanguage.get());

        userRepository.save(editUser);
        return new ApiResponse("Saved successfully", true);


    }

    //     Delete
    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

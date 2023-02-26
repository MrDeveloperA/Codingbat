package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.diler.AnswerDto;

import com.example.codingbat.entity.Answer;
import com.example.codingbat.entity.Theme;
import com.example.codingbat.entity.User;
import com.example.codingbat.repository.AnswerRepository;
import com.example.codingbat.repository.ThemeRepository;
import com.example.codingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ThemeRepository themeRepository;


    //      Create
    public ApiResponse addAnswer(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setAnswerCode(answerDto.getAnswerCode());

        Optional<User> optionalUser = userRepository.findById(answerDto.getUser());
        if (!optionalUser.isPresent())
            return new ApiResponse("Not found", false);
        answer.setUser(optionalUser.get());

        Optional<Theme> optionalTheme = themeRepository.findById(answerDto.getTheme());
        if (!optionalTheme.isPresent())
            return new ApiResponse("Not found", false);
        answer.setTheme(optionalTheme.get());

        answerRepository.save(answer);
        return new ApiResponse("Saved successfully", true);
    }

    //  Get
    public List<Answer> getAnswer() {
        return answerRepository.findAll();
    }

    //    Get by id
    public Answer getAnswerById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return null;
        return optionalAnswer.get();
    }

    //    Update
    public ApiResponse editAnswer(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Not found", false);
        Answer editAnswer = optionalAnswer.get();

        editAnswer.setAnswerCode(answerDto.getAnswerCode());

        Optional<User> optionalUser = userRepository.findById(answerDto.getUser());
        if (!optionalUser.isPresent())
            return new ApiResponse("Not found", false);
        editAnswer.setUser(optionalUser.get());
        answerRepository.save(editAnswer);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteAnswer(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

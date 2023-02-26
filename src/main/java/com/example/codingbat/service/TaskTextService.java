package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Task;
import com.example.codingbat.entity.TaskText;
import com.example.codingbat.repository.TaskRepository;
import com.example.codingbat.repository.TaskTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskTextService {
    @Autowired
    TaskTextRepository taskTextRepository;


    //      Create
    public ApiResponse addTaskText(TaskText taskText) {
        TaskText taskText1 = new TaskText();
        taskText1.setText(taskText.getText());

        taskTextRepository.save(taskText1);
        return new ApiResponse("Saved successfully", true);

    }

    //  Get
    public List<TaskText> getTaskText() {
        return taskTextRepository.findAll();
    }

    //    Get by id
    public TaskText getTaskTextById(Integer id) {
        Optional<TaskText> optionalTaskText = taskTextRepository.findById(id);
        if (!optionalTaskText.isPresent())
            return null;
        return optionalTaskText.get();
    }

    //    Update
    public ApiResponse editTaskText(Integer id, TaskText taskText) {
        Optional<TaskText> optionalTaskText = taskTextRepository.findById(id);
        if (!optionalTaskText.isPresent())
            return new ApiResponse("Not found", false);

        TaskText editTaskText = optionalTaskText.get();
        editTaskText.setText(taskText.getText());

        taskTextRepository.save(editTaskText);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteTaskText(Integer id) {
        try {
            taskTextRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

package com.example.codingbat.service;

import com.example.codingbat.diler.ApiResponse;
import com.example.codingbat.entity.Declare;
import com.example.codingbat.entity.Task;
import com.example.codingbat.repository.DeclareRepository;
import com.example.codingbat.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;


    //      Create
    public ApiResponse addTask(Task task) {
        Task task1 = new Task();
        task1.setText(task.getText());

        taskRepository.save(task1);
        return new ApiResponse("Saved successfully", true);

    }

    //  Get
    public List<Task> getTask() {
        return taskRepository.findAll();
    }

    //    Get by id
    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return null;
        return optionalTask.get();
    }

    //    Update
    public ApiResponse editTask(Integer id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("Not found", false);

        Task editTask = optionalTask.get();
        editTask.setText(task.getText());

        taskRepository.save(editTask);
        return new ApiResponse("Saved successfully", true);
    }

    //     Delete
    public ApiResponse deleteTask(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            return new ApiResponse("Error in deleting", false);
        }
    }
}

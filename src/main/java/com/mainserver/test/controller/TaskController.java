package com.mainserver.test.controller;

import com.mainserver.test.dto.TaskDto;
import com.mainserver.test.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.List;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task/")
    public List<TaskDto> show() {
        return taskService.findAll();
    }

    @GetMapping("/task/get/{id}")
    public TaskDto findTaskWithId(@PathVariable(name = "id") long id) {
        return taskService.findById(id);
    }

    @GetMapping("/task/getByUser/{id}")
    public List<TaskDto> findTasksByUserId(@PathVariable(name = "id") long userId) {
        return taskService.findByUserId(userId);
    }

    @PostMapping("/task/addJson")
    public void add(@RequestBody TaskDto taskDto) {
        if (taskDto == null) {
            throw new InvalidParameterException();
        }
        taskService.addTask(taskDto);
    }
}

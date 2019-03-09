package com.mainserver.test.service;

import com.mainserver.test.dto.TaskDto;
import com.mainserver.test.repository.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public void setUserRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(TaskDto taskDto) {
        taskRepository.add(taskDto);
    }

    public List<TaskDto> findAll() {
        return taskRepository.findAll();
    }

    public TaskDto findById(long taskId) {
        return taskRepository.findByTaskId(taskId);
    }

    public List<TaskDto> findByUserId(long userId) {
        return taskRepository.findByUserId(userId);
    }
}

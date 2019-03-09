package com.mainserver.test.repository.task;

import com.mainserver.test.dto.TaskDto;

import java.util.List;

public interface TaskRepository {
    List<TaskDto> findAll();

    TaskDto findByTaskId(long id);

    List<TaskDto> findByUserId(long userId);

    void add(TaskDto taskDto);
}

package com.mainserver.test.repository.task;

import com.mainserver.test.dto.TaskDto;
import com.mainserver.test.entity.Task;
import com.mainserver.test.entity.User;
import com.mainserver.test.exception.TaskNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DbTaskRepository implements TaskRepository {
    @Autowired
    private TaskJpaRepository repository;

    @Override
    public List<TaskDto> findAll() {
        return fromEntitiesToDtos(repository.findAll());
    }

    @Override
    public TaskDto findByTaskId(long id) {
        Optional<Task> task = repository.findById(id);
        if (!task.isPresent()) {
            throw new TaskNotFoundException();
        }
        return fromEntityToDto(task.get());
    }

    @Override
    public List<TaskDto> findByUserId(long userId) {
        User user = new User();
        user.setId(userId);
        return fromEntitiesToDtos(repository.findByUser(user));
    }

    @Override
    public void add(TaskDto taskDto) {
        repository.save(fromDtoToEntity(taskDto));
    }

    private TaskDto fromEntityToDto(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDto(task.getName(), task.getCreated(), task.getUser());
    }

    private List<TaskDto> fromEntitiesToDtos(List<Task> tasks) {
        if (tasks == null) {
            return null;
        }
        return tasks.stream().map(this::fromEntityToDto).collect(Collectors.toList());
    }

    private Task fromDtoToEntity(TaskDto taskDto) {
        if (taskDto == null) {
            return null;
        }
        return new Task(taskDto.getName(), taskDto.getCreated(), taskDto.getUser());
    }
}

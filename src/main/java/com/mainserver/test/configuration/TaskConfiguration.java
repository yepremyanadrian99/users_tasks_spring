package com.mainserver.test.configuration;

import com.mainserver.test.repository.task.DbTaskRepository;
import com.mainserver.test.repository.task.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfiguration {
    @Bean
    public TaskRepository taskRepository() {
        return new DbTaskRepository();
    }
}

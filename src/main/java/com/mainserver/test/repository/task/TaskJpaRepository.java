package com.mainserver.test.repository.task;

import com.mainserver.test.entity.Task;
import com.mainserver.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskJpaRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}

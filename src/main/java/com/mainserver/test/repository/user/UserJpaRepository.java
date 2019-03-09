package com.mainserver.test.repository.user;

import com.mainserver.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}

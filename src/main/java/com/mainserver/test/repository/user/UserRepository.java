package com.mainserver.test.repository.user;

import com.mainserver.test.dto.UserDto;

import java.util.List;

public interface UserRepository {
    UserDto findByUserId(long id);

    List<UserDto> findAll();

    UserDto add(UserDto user);
}

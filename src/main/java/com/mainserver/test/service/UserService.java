package com.mainserver.test.service;

import com.mainserver.test.dto.UserDto;
import com.mainserver.test.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserDto user) {
        userRepository.add(user);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll();
    }

    public UserDto findByUserId(long id) {
        return userRepository.findByUserId(id);
    }
}

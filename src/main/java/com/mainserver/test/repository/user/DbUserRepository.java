package com.mainserver.test.repository.user;

import com.mainserver.test.dto.UserDto;
import com.mainserver.test.entity.User;
import com.mainserver.test.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DbUserRepository implements UserRepository {
    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public UserDto findByUserId(long id) {
        Optional<User> user = userJpaRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return fromEntityToDto(user.get());
    }

    @Override
    public List<UserDto> findAll() {
        return fromEntititesToDtos(userJpaRepository.findAll());
    }

    @Override
    public UserDto add(UserDto userDto) {
        return fromEntityToDto(userJpaRepository.save(fromDtoToEntity(userDto)));
    }

    private UserDto fromEntityToDto(User user) {
        return new UserDto(user.getName(), user.getSurname(), user.getPhone());
    }

    private List<UserDto> fromEntititesToDtos(List<User> users) {
        return users.stream().map(this::fromEntityToDto).collect(Collectors.toList());
    }

    private User fromDtoToEntity(UserDto userDto) {
        return new User(userDto.getName(), userDto.getSurname(), userDto.getPhone());
    }
}

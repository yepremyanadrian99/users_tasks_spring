package com.mainserver.test.repository.user;

import com.mainserver.test.dto.UserDto;
import com.mainserver.test.entity.User;
import com.mainserver.test.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository implements UserRepository {
    private static long GLOBAL_ID = 1;
    private List<User> userDatabase;

    {
        userDatabase = new ArrayList<>();
        userDatabase.add(new User(GLOBAL_ID++, "Adas", "Yepremyan", "044444777"));
        userDatabase.add(new User(GLOBAL_ID++, "Evik", "Yeshilbashyan", "077080958"));
    }

    @Override
    public UserDto add(UserDto userDto) {
        User user = new User(GLOBAL_ID++, userDto.getName(), userDto.getSurname(), userDto.getPhone());
        userDatabase.add(user);
        return userDto;
    }

    @Override
    public UserDto findByUserId(long id) {
        Optional<User> user = userDatabase.stream().filter(x -> x.getId() == id).findFirst();
        if(!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return fromEntityToDto(user.get());
    }

    @Override
    public List<UserDto> findAll() {
        return fromEntitiesToDtos(userDatabase);
    }

    private UserDto fromEntityToDto(User user) {
        if (user == null) {
            throw new UserNotFoundException();
        }
        return new UserDto(user.getName(), user.getSurname(), user.getPhone());
    }

    private List<UserDto> fromEntitiesToDtos(List<User> users) {
        return users.stream().map(this::fromEntityToDto).collect(Collectors.toList());
    }
}

package com.mainserver.test.controller;

import com.mainserver.test.dto.ErrorDto;
import com.mainserver.test.dto.UserDto;
import com.mainserver.test.enums.Error;
import com.mainserver.test.exception.UserNotFoundException;
import com.mainserver.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/")
    public List<UserDto> show() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public UserDto show(@PathVariable long id) {
        if (id == 0) {
            throw new InvalidParameterException();
        }
        return userService.findByUserId(id);
    }

    @PostMapping(value = "/user/addJson", consumes = "application/json")
    public void add(@RequestBody UserDto userDto) {
        if (userDto == null ||
                userDto.getName() == null || userDto.getSurname() == null || userDto.getPhone() == null ||
                userDto.getName().isEmpty() || userDto.getSurname().isEmpty() || userDto.getPhone().isEmpty()) {
            throw new InvalidParameterException();
        }
        userService.addUser(userDto);
    }

    @PostMapping(value = "/user/addParams")
    public void add(@RequestParam(name = "name", required = false) String name,
                    @RequestParam(name = "surname", required = false) String surname,
                    @RequestParam(name = "phone", required = false) String phone) {
        if (name == null || surname == null || phone == null ||
                name.isEmpty() || surname.isEmpty() || phone.isEmpty()) {
            throw new InvalidParameterException();
        }
        userService.addUser(new UserDto(name, surname, phone));
    }

    @PostMapping(value = "/user/addBody")
    public void add(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phone = request.getParameter("phone");
        if (name == null || surname == null || phone == null ||
                name.isEmpty() || surname.isEmpty() || phone.isEmpty()) {
            throw new InvalidParameterException();
        }
        userService.addUser(new UserDto(name, surname, phone));
    }

    @ExceptionHandler({InvalidParameterException.class, UserNotFoundException.class})
    public ResponseEntity<?> ExceptionHandler(Exception e) {
        Error error = getError(e);
        return new ResponseEntity<>(new ErrorDto(error.getCode(), error.getDescription()), getStatus(e));
    }

    private HttpStatus getStatus(Exception e) {
        if (e instanceof UserNotFoundException)
            return HttpStatus.NOT_FOUND;
        else if (e instanceof InvalidParameterException)
            return HttpStatus.BAD_REQUEST;
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private Error getError(Exception e) {
        if (e instanceof UserNotFoundException)
            return Error.UserNotFound;
        else if (e instanceof InvalidParameterException)
            return Error.InvalidParameter;
        return Error.InternalServerError;
    }
}

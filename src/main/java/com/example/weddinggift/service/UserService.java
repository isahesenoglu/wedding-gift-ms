package com.example.weddinggift.service;

import com.example.weddinggift.dto.UserDto;
import com.example.weddinggift.exception.EmailAlreadyExistException;
import com.example.weddinggift.exception.LoginInvalidException;
import com.example.weddinggift.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void createUser(User user) throws EmailAlreadyExistException;

    List<UserDto> users();

    String login(String email, String password) throws LoginInvalidException;

}

package com.example.weddinggift.controller;

import com.example.weddinggift.dto.ErrorResponseDto;
import com.example.weddinggift.dto.LoginRequestDto;
import com.example.weddinggift.dto.ResponseDto;
import com.example.weddinggift.dto.UserDto;
import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.exception.EmailAlreadyExistException;
import com.example.weddinggift.exception.LoginInvalidException;
import com.example.weddinggift.model.User;
import com.example.weddinggift.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/v1")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userServiceImpl.users();
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws EmailAlreadyExistException {
            userServiceImpl.createUser(user);

        return ResponseEntity.status(201).body(new ResponseDto("SUCCESS","İstifadəçi uğurla qeydiyyatdan keçdi"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) throws LoginInvalidException {
            String activeToken = userServiceImpl.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
            return ResponseEntity.ok(new ResponseDto("SUCCESS", activeToken));
    }

}

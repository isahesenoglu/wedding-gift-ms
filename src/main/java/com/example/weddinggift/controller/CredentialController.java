package com.example.weddinggift.controller;

import com.example.weddinggift.dto.CredentialRequestDto;
import com.example.weddinggift.dto.CredentialResponseDto;
import com.example.weddinggift.dto.ErrorResponseDto;
import com.example.weddinggift.dto.ResponseDto;
import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.model.Credential;
import com.example.weddinggift.service.impl.CredentialServiceImpl;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
public class CredentialController {

    private final CredentialServiceImpl credentialServiceImpl;

    @GetMapping("/users/{userId}/credentials")
    public ResponseEntity<CredentialResponseDto> getCredentialsByUserId(@PathVariable Long userId) throws CredentialNullException {
        CredentialResponseDto credential = credentialServiceImpl.getCredentialByUserId(userId);
        return ResponseEntity.ok(credential);
    }


    @PostMapping("/api/wedding-gift-ms/credentials")
    public ResponseEntity<ResponseDto> createCredential(@RequestBody CredentialRequestDto credentialRequestDto) throws CredentialNullException {

            String token = credentialServiceImpl.createCredential(credentialRequestDto);
            return ResponseEntity.ok(new ResponseDto("SUCCESS", token));
    }
    }


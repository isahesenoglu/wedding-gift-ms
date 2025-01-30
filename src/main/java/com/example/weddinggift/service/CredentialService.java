package com.example.weddinggift.service;

import com.example.weddinggift.dto.CredentialRequestDto;
import com.example.weddinggift.dto.CredentialResponseDto;
import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.model.Credential;
import com.example.weddinggift.model.User;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;
import java.util.Optional;

public interface CredentialService {

    String createCredential(CredentialRequestDto credentialRequestDto) throws CredentialNullException;

    CredentialResponseDto getCredentialByUserId(Long userId) throws  CredentialNullException;

    Optional<User> validateTokenAndGetUser(String token);

}

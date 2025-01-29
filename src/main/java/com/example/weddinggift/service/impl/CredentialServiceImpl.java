package com.example.weddinggift.service.impl;

import com.example.weddinggift.dto.CredentialRequestDto;
import com.example.weddinggift.dto.CredentialResponseDto;
import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.model.Credential;
import com.example.weddinggift.model.User;
import com.example.weddinggift.repository.CredentialRepository;
import com.example.weddinggift.repository.UserRepository;
import com.example.weddinggift.service.CredentialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private CredentialRepository credentialRepository;
    private UserRepository userRepository;

    @Override
    public String createCredential(CredentialRequestDto credentialRequestDto) throws CredentialNullException {
        User user = userRepository.findById(credentialRequestDto.getUserId())
                .orElseThrow(() -> new CredentialNullException("user_not_found"));

        UserServiceImpl.deleteOldTokens(user, credentialRepository);

        Credential credential = new Credential();
        credential.setToken(UUID.randomUUID().toString());
        credential.setUser(user);
        credential.setInsertDate(LocalDateTime.now());
        credential.setUpdateDate(LocalDateTime.now());

        Credential savedCredential = credentialRepository.save(credential);

        return savedCredential.getToken();
    }



    @Override
    public CredentialResponseDto getCredentialByUserId(Long userId) throws CredentialNullException {
        Optional<Credential> credential = credentialRepository.findActiveCredentialsByUserId(userId);

        return credential
                .map(c -> new CredentialResponseDto(c.getToken()))
                .orElseThrow(() -> new CredentialNullException("user_not_found"));

    }

    @Override
    public Optional<User> validateTokenAndGetUser(String token) {
        return credentialRepository.findByToken(token)
                .filter(credential -> credential.getStatus() == 1 && credential.getDataStatus() == 1)
                .map(Credential::getUser); // Tokenə bağlı olan user-i qaytar
    }

}

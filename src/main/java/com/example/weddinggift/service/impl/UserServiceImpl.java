package com.example.weddinggift.service.impl;

import com.example.weddinggift.dto.UserDto;
import com.example.weddinggift.exception.EmailAlreadyExistException;
import com.example.weddinggift.exception.LoginInvalidException;
import com.example.weddinggift.model.Credential;
import com.example.weddinggift.model.User;
import com.example.weddinggift.repository.CredentialRepository;
import com.example.weddinggift.repository.UserRepository;
import com.example.weddinggift.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private CredentialRepository credentialRepository;

    @Override
    public void createUser(User user) throws EmailAlreadyExistException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistException("user_already_exist");
        }
        userRepository.save(user);
    }

    @Override
    public List<UserDto> users() {

        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDto(
                        user.getName(),
                        user.getSurname(),
                        user.getMiddleName(),
                        user.getEmail(),
                        user.getMobile(),
                        user.getGender(),
                        user.getFirstPayment(),
                        user.getWeddingDate(),
                        user.getStatus(),
                        user.getDataStatus(),
                        user.getInsertDate(),
                        user.getUpdateDate()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public String login(String email, String password) throws LoginInvalidException {
        User user = userRepository.findByEmailAndPassword(email,password);
        if(user == null){
            throw  new LoginInvalidException("invalid_user_data");
        }
        deleteOldTokens(user, credentialRepository);

        String activeToken = UUID.randomUUID().toString();

        Credential credential = new Credential();
        credential.setUser(user);
        credential.setToken(activeToken);
        credential.setInsertDate(LocalDateTime.now());
        credential.setUpdateDate(LocalDateTime.now());
        credentialRepository.save(credential);

        return activeToken;

    }

    static void deleteOldTokens(User user, CredentialRepository credentialRepository) {
        List<Credential> activeCredentials = credentialRepository.findByUserAndStatusAndDataStatus(
                user, 1, 1);
        if (!activeCredentials.isEmpty()) {
            for (Credential activeCredential : activeCredentials) {
                activeCredential.setStatus(0);
                activeCredential.setDataStatus(0);
                credentialRepository.save(activeCredential);
            }
        }
    }

}

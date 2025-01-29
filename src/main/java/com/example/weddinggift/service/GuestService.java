package com.example.weddinggift.service;

import com.example.weddinggift.dto.CredentialRequestDto;
import com.example.weddinggift.dto.GuestRequestDto;
import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.exception.GuestAlreadyExistException;
import com.example.weddinggift.model.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestService {

    List<Guest> guests(String token) throws CredentialNullException;

    void createGuest(String token, GuestRequestDto guestRequestDto) throws CredentialNullException, GuestAlreadyExistException;

    Optional<Guest> guestById(String token, Long id) throws CredentialNullException;
}

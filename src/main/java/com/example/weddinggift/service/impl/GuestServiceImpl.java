package com.example.weddinggift.service.impl;

import com.example.weddinggift.dto.GuestRequestDto;
import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.exception.GuestAlreadyExistException;
import com.example.weddinggift.model.Guest;
import com.example.weddinggift.model.User;
import com.example.weddinggift.repository.GuestRepository;
import com.example.weddinggift.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;
    private CredentialServiceImpl credentialServiceImpl; // Token yoxlanışı üçün UserService lazımdır

    @Override
    public void createGuest(String token, GuestRequestDto guestRequestDto) throws CredentialNullException, GuestAlreadyExistException {
        // Tokeni yoxla və istifadəçini əldə et
        User user = validateTokenAndGetUser(token);

        // Eyni guestin mövcud olub-olmadığını yoxla
        Optional<Guest> existingGuest = guestRepository.findExistingGuest(
                guestRequestDto.getName(),
                guestRequestDto.getSurname(),
                guestRequestDto.getNickName(),
                guestRequestDto.getAmount(),
                guestRequestDto.getCurrency().name(),
                guestRequestDto.getPaymentType().name(),
                guestRequestDto.getAdditionalText(),
                user.getId() // userId artıq tokenlə bağlıdır
        );

        if (existingGuest.isPresent()) {
            throw new GuestAlreadyExistException("guest_already_exist");
        }

        // Yeni guest yarat
        Guest guest = new Guest();
        guest.setName(guestRequestDto.getName());
        guest.setSurname(guestRequestDto.getSurname());
        guest.setNickName(guestRequestDto.getNickName());
        guest.setAmount(guestRequestDto.getAmount());
        guest.setCurrency(guestRequestDto.getCurrency().name());
        guest.setPaymentType(guestRequestDto.getPaymentType().name());
        guest.setAdditionalText(guestRequestDto.getAdditionalText());
        guest.setUser(user); // Tokenlə tapılan istifadəçiyə bağlanır
        guest.setStatus(1);
        guest.setDataStatus(1);
        guest.setInsertDate(LocalDateTime.now());
        guest.setUpdateDate(LocalDateTime.now());

        guestRepository.save(guest);
    }
    @Override
    public List<Guest> guests(String token) throws CredentialNullException {
        // Tokeni yoxla və istifadəçini əldə et
        User user = validateTokenAndGetUser(token);

        // Token təsdiqləndi, bağlı olan istifadəçinin bütün guestlərini qaytar
        return guestRepository.findByUser(user);
    }

    @Override
    public Optional<Guest> guestById(String token, Long id) throws CredentialNullException {
        // Tokeni yoxla və istifadəçini əldə et
        User user = validateTokenAndGetUser(token);

        // Guesti tap və istifadəçiyə məxsus olub-olmadığını yoxla
        return guestRepository.findById(id)
                .filter(guest -> guest.getUser().getId().equals(user.getId())); // İstifadəçiyə məxsus olmalıdır
    }

    /**
     * Tokenin düzgünlüyünü yoxlayan metod.
     */
    private User validateTokenAndGetUser(String token) throws CredentialNullException {
        return credentialServiceImpl.validateTokenAndGetUser(token)
                .orElseThrow(() -> new CredentialNullException("Invalid or expired token"));
    }

}

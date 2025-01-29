package com.example.weddinggift.controller;

import com.example.weddinggift.dto.GuestRequestDto;
import com.example.weddinggift.dto.ResponseDto;
import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.exception.EmailAlreadyExistException;
import com.example.weddinggift.exception.GuestAlreadyExistException;
import com.example.weddinggift.model.Guest;
import com.example.weddinggift.model.User;
import com.example.weddinggift.service.impl.GuestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class GuestController {

    private final GuestServiceImpl guestServiceImpl;

    @GetMapping("/guests")
    public ResponseEntity<List<Guest>> getGuests(@RequestHeader("Authorization") String token) throws CredentialNullException {
        List<Guest> guests = guestServiceImpl.guests(token);
        return ResponseEntity.ok(guests);
    }

    /**
     * Yeni qonaq əlavə edir (token yoxlanılır).
     */
    @PostMapping("/guests")
    public ResponseEntity<ResponseDto> createGuest(
            @RequestHeader("Authorization") String token,
            @RequestBody GuestRequestDto guestRequestDto
    ) throws CredentialNullException, GuestAlreadyExistException {
        guestServiceImpl.createGuest(token, guestRequestDto);
        return ResponseEntity.ok(new ResponseDto("SUCCESS", "Qonaq uğurla əlavə olundu"));
    }

    /**
     * ID-yə görə qonaq məlumatlarını gətirir (token yoxlanılır).
     */
    @Operation(
            summary = "Get guest by ID",
            description = "Fetches a guest's information by their unique ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Guest found successfully"),
                    @ApiResponse(responseCode = "404", description = "Guest not found")
            }
    )

    @GetMapping("/guests/{guestId}")
    public Optional<Guest> getGuestById(@RequestHeader("Authorization") String token, @PathVariable Long guestId) throws CredentialNullException {
        return guestServiceImpl.guestById(token, guestId);
    }

}
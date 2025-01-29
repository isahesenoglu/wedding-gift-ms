package com.example.weddinggift.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestAlreadyExistException extends Exception{
    private String message;

}

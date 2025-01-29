package com.example.weddinggift.dto;

import com.example.weddinggift.exception.CredentialNullException;
import com.example.weddinggift.exception.EmailAlreadyExistException;
import com.example.weddinggift.exception.GuestAlreadyExistException;
import com.example.weddinggift.exception.LoginInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("FAILED",ex.getMessage(),"Bu email ilə istifadəçi artıq mövcuddur");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(CredentialNullException.class)
    public ResponseEntity<ErrorResponseDto> handleCredentialNullException(CredentialNullException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("FAILED",ex.getMessage(),"Belə istifadəçi yoxdur");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }
    @ExceptionHandler(LoginInvalidException.class)
    public ResponseEntity<ErrorResponseDto> handleLoginInvalidException(LoginInvalidException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("FAILED",ex.getMessage(),"Email və ya parol yanlışdır");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(GuestAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleGuestAlreadyExistException(GuestAlreadyExistException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto("FAILED",ex.getMessage(),"Bu qonaq artıq əlavə olunub");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto("FAILED",ex.getMessage(),"Gözlənilməyən xəta baş verdi"));
    }
}

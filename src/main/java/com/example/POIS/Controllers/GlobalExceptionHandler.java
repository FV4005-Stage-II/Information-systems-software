package com.example.POIS.Controllers;


import com.example.POIS.dto.response.BadRequestResponse;
import com.example.POIS.service.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<BadRequestResponse> catchBadRequest(BadRequestException ex) {
        log.warn("Bad request : " + ex.getMessage());
        return new ResponseEntity<>(new BadRequestResponse(), HttpStatus.BAD_REQUEST);
    }
}

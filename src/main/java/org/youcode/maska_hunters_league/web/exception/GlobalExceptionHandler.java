package org.youcode.maska_hunters_league.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.youcode.maska_hunters_league.web.exception.competition.CompetitionAlreadyExistException;
import org.youcode.maska_hunters_league.web.exception.competition.CompetitionInSameWeekException;
import org.youcode.maska_hunters_league.web.exception.species.InvalidSpeciesException;
import org.youcode.maska_hunters_league.web.exception.species.SpeciesNotFoundException;
import org.youcode.maska_hunters_league.web.exception.user.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //User exceptions handler

    @ExceptionHandler(InvalidUserExeption.class)
    public ResponseEntity<String> handleInvalidUserException(InvalidUserExeption ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNameAlreadyExistException.class)
    public ResponseEntity<String> handleUserNameAlreadyExistException(UserNameAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<String> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserDoesntExistException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //Species exceptions handler

    @ExceptionHandler(InvalidSpeciesException.class)
    public ResponseEntity<String> handleInvalidSpeciesException(InvalidSpeciesException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SpeciesNotFoundException.class)
    public ResponseEntity<String> handelSpeciesNotFoundException(SpeciesNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Competition exceptions handler

    @ExceptionHandler(CompetitionInSameWeekException.class)
    public ResponseEntity<String> handleCompetitionInSameWeekException(CompetitionInSameWeekException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CompetitionAlreadyExistException.class)
    public ResponseEntity<String> handelCompetitionAlreadyExistException(CompetitionAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    }

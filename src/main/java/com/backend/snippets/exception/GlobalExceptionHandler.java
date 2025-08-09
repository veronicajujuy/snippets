package com.backend.snippets.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> manageResourceNotFoundException(NotFoundException ex,
                                                                    HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> manageBadRequestException(BadRequestException ex,
                                                                    HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> manageConflictException(ConflictException ex,
                                                                    HttpServletRequest request) {
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(),
                List.of()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> manejarValidaciones (MethodArgumentNotValidException e, HttpServletRequest request){
        List<String> errores = new ArrayList<>();
        for(Object error: e.getBindingResult().getAllErrors()){
            if(error instanceof FieldError){
                FieldError fieldError = (FieldError) error;
                String fieldName = fieldError.getField();
                String message = fieldError.getDefaultMessage();
                errores.add(fieldName+": "+message);
            }
        }
        ApiError apiError = new ApiError(
                request.getRequestURI(),
                "validation field",
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                errores
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarTodasLasExcepciones(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}

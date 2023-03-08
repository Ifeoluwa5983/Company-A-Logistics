package com.companyA.logistics.exception;

import com.companyA.logistics.dto.ErrorDTO;
import com.companyA.logistics.security.UnsuccessfulLogin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Objects;

@ControllerAdvice
public class ValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
        ErrorDTO error = new ErrorDTO("Method Argument Not Valid", Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value
            = CompanyAException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    UnsuccessfulLogin handleException(CompanyAException ex)
    {
        return new UnsuccessfulLogin(LocalDateTime.now(), ex.getMessage(), HttpStatus.BAD_REQUEST.toString() ,"");
    }
}

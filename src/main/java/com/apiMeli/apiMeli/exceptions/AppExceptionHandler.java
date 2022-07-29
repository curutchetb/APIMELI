package com.apiMeli.apiMeli.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.apiMeli.apiMeli.models.responses.ErrorMessage;
import com.apiMeli.apiMeli.models.responses.ValidationErrors;

/**
 * Clase encargada de controlar todas las excepciones especificas
 * @author Barbara
 *
 */
@ControllerAdvice
public class AppExceptionHandler {

	/**
	 * Método que personaliza el retorno de la excepcion EmailExistsException
	 * @param ex EmailExistsException
	 * @param webRequest WebRequest
	 * @return devuelve el error personalizado
	 */
    @ExceptionHandler(value = EmailExistsException.class)
    public ResponseEntity<Object> handleEmailExistsException(EmailExistsException ex, WebRequest webRequest) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Método que personaliza el retorno de la excepcion MethodArgumentNotValidException
     * @param ex MethodArgumentNotValidException
     * @param webRequest
     * @return devuelve el error personalizado
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleEmailExistsException(MethodArgumentNotValidException ex,
            WebRequest webRequest) {

        Map<String, String> errors = new HashMap<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        ValidationErrors validationErrors = new ValidationErrors(errors, new Date());

        return new ResponseEntity<>(validationErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Método que personaliza excepcion ItemExistanceException
     * @param ex ItemExistanceException
     * @param webRequest WebRequest
     * @return devuelve el error personalizado
     */
    @ExceptionHandler(value = ItemExistanceException.class)
    public ResponseEntity<Object> handleItemExistsException(ItemExistanceException ex, WebRequest webRequest) {

        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Método que personaliza cualquier origen de excepcion
     * @param ex Exception
     * @param webRequest WebRequest
     * @return devuelve el error personalizado
     */
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleException(Exception ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

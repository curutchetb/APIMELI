package com.apiMeli.apiMeli.exceptions;

/**
 * Clase especifica para cuando se genera una excepcion del tipo de EmailExistsException
 * donde el email no existe
 * @author Barbara
 *
 */
public class EmailExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor del mensaje que genera error
     * @param message mensaje
     */
    public EmailExistsException(String message) {
        super(message);
    }
}

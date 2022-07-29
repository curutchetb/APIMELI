package com.apiMeli.apiMeli.exceptions;

/**
 * Clase especifica para cuando se genera una excepcion del tipo de ItemExistanceException
 * donde el item no existe
 * @author Barbara
 *
 */
public class ItemExistanceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor del mensaje que genera error
     * @param message mensaje
     */
    public ItemExistanceException(String message) {
        super(message);
    }
    
}

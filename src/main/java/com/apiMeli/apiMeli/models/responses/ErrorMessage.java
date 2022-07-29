package com.apiMeli.apiMeli.models.responses;

import java.util.Date;

/**
 * Clase encargada de dar respuesta personalizada a excepciones generadas
 * @author Barbara
 *
 */
public class ErrorMessage {

    private Date timestamp;
    private String message;

    /**
     * Constructor que recibe fecha y mensaje
     * @param timestamp fecha
     * @param message mensaje
     */
    public ErrorMessage(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    /**
     * Constructor
     */
    public ErrorMessage() {

    }

    /**
     * Getter fecha
     * @return fecha
     */
    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * Setter fecha
     * @param timestamp fecha
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Getter mensaje
     * @return mensaje
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Setter mensaje
     * @param message mensaje
     */
    public void setMessage(String message) {
        this.message = message;
    }

}

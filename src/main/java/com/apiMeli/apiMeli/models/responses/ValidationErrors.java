package com.apiMeli.apiMeli.models.responses;

import java.util.Date;
import java.util.Map;

/**
 * Clase encargada de dar respuesta de todos los errores que se generaron junto con la fecha
 * @author Barbara
 *
 */
public class ValidationErrors {

    private Map<String, String> errors;
    private Date timestamp;

    /**
     * Constructor ValidationErrors
     * @param errors conjunto de errores
     * @param timestamp fecha del conjunto de errores
     */
    public ValidationErrors(Map<String, String> errors, Date timestamp) {
        this.errors = errors;
        this.timestamp = timestamp;
    }

    /**
     * Getter conjunto de errores
     * @return conjunto de errores
     */
    public Map<String, String> getErrors() {
        return this.errors;
    }

    /**
     * Setter conjunto de errores
     * @param errors conjunto de errores
     */
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    /**
     * Getter fecha del conjunto de errores
     * @return fecha del conjunto de errores
     */
    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * Setter fecha del conjunto de errores
     * @param timestamp fecha del conjunto de errores
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

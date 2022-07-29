package com.apiMeli.apiMeli.models.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Clase encargada de convertir objeto Json a objeto JAVA
 * Recibe los datos enviados por el usuario para crear un usuario
 * @author Barbara
 *
 */
public class UserDetailRequestModel {

    @NotEmpty(message = "El nombre es obligatorio")
    private String firstName;

    @NotEmpty(message = "El apellido es obligatorio")
    private String lastName;

    @NotEmpty(message = "El correo electronico es obligatorio")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "correo electronico debe ser valido")
    private String email;

    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 8, message = "La contraseña debe tener entre 4 y 8 caracteres")
    private String password;

    /**
     * Getter nombre del user
     * @return retorna nombre
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter del user 
     * @param firstName setea nombre
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter apellido del user
     * @return retorna apellido
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter del user
     * @param lastName setea apellido
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter email del user
     * @return retorna email del user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter email del user
     * @param email setea email del user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter password del user
     * @return retorna contraseña del user
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Setter password del user
     * @param password setea contraseña del user
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

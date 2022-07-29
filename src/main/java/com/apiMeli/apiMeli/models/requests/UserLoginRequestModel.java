package com.apiMeli.apiMeli.models.requests;

/**
 * Clase encargada de convertir objeto Json a objeto JAVA
 * Recibe los datos enviados por el usuario para realizar el Login
 * @author Barbara
 *
 */
public class UserLoginRequestModel {

    private String email;
    private String password;

    /**
     * Getter del email del user
     * @return email del user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter del email del user
     * @param email email del user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter del password del user
     * @return password del user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter del password del user
     * @param password password del user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

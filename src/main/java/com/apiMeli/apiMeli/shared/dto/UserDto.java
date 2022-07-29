package com.apiMeli.apiMeli.shared.dto;

import java.io.Serializable;
import java.util.List;


/**
 * DTO Data Transfer Object en relacion a los datos del user.
 * Clase que vincula los distintos layers del codigo en relacion a los datos del user.
 * Conectada con la base de datos y la logica de nuestro sistema
 * @author Barbara
 *
 */
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private List<ItemDto> items;

    /**
     * Getter id autogenerado
     * @return id id autogenerado del user
     */
    public long getId() {
        return this.id;
    }

    /**
     * Setter del id autogenerado
     * @param id id autogenerado
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter userId publico
     * Se genera un UUID para hacerlo mas seguro ante
     * un ataque cibernetico
     * @return userId userId publico
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Setter userId publico
     * Se genera un UUID para hacerlo mas seguro ante
     * un ataque cibernetico
     * @param userId userId del user
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter nombre del user
     * @return nombre del user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter nombre del user
     * @param firstName nombre del user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter apellido del user
     * @return apellido del user
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter apellido del user
     * @param lastName apellido del user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter del email del user
     * @return email del user
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter email del user
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
     * Setter password del user
     * @param password password del user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter de password encriptada del user
     * @return password encriptada del user
     */
    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

    /**
     * Setter password encriptada del user
     * @param encryptedPassword password encriptada del user
     */
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
    
    /**
     * Getter lista de items favoritos del user
     * @return lista de items favoritos del user
     */
    public List<ItemDto> getItems() {
        return this.items;
    }

    /**
     * Setter lista de items favoritos del user
     * @param items lista de items favoritos del user
     */
    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

}

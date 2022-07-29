package com.apiMeli.apiMeli.models.responses;

import java.util.List;

/**
 * Clase encargada de dar respuesta de los datos del usuario
 * sin contraseña para no devolver datos sensibles
 * @author Barbara
 *
 */
public class UserRest {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<ItemRest> items;

    /**
     * Getter de userIdpublico
     * Se genera un UUID para hacerlo mas seguro ante
     * un ataque cibernetico
     * @return retorna userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Setter del userId publico
     * Se genera un UUID para hacerlo mas seguro ante
     * un ataque cibernetico
     * @param userId setea userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter de nombre del user
     * @return retorna nombre
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter de nombre del user
     * @param firstName setea nombre
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter de apellido del user
     * @return retorna apellido
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter de apellido del user
     * @param lastName setea apellido
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter de email del user
     * @return retorna email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter de email del user
     * @param email setea email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter de la lista de items favoritos del user
     * @return retorna lista de favoritos
     */
    public List<ItemRest> getItems() {
        return this.items;
    }

    /**
     * Setter de lista de items favoritos del user
     * @param items setea lista de items favoritos
     */
    public void setItems(List<ItemRest> items) {
        this.items = items;
    }

}

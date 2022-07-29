package com.apiMeli.apiMeli.shared.dto;

import java.io.Serializable;
/**
 * DTO Data Transfer Object en relacion a los items.
 * Clase que vincula los distintos layers del codigo en relacion a los items.
 * Conectada con la base de datos y la logica de nuestro sistema
 * @author Barbara
 *
 */
public class ItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    private double price;

    // relacion con userDto
    private UserDto user;

    /**
     * Getter id del item
     * @return id del item
     */
    public String getItemId() {
        return this.itemId;
    }

    /**
     * Setter id del item
     * @param itemId id del item
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * Getter precio del item
     * @return precio del item
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Setter del precio el item
     * @param price precio el item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter de los datos del user que tiene el item
     * @return datos del user que tiene el item
     */
    public UserDto getUser() {
        return this.user;
    }

    /**
     * Setter de los datos del user que tiene el item
     * @param user datos del user que tiene el item
     */
    public void setUser(UserDto user) {
        this.user = user;
    }

}

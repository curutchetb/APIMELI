package com.apiMeli.apiMeli.models.responses;

/**
 * Clase encargada de dar respuesta de los datos de los items
 * @author Barbara
 *
 */
public class ItemRest {

    private String itemId;

    private double price;

    private UserRest user;

    /**
     * Getter precio del item
     * @return precio del item
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Setter precio del item
     * @param price precio del item
     */
    public void setPrice(double price) {
        this.price = price;
    }

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
     * Getter datos del user que tiene el item
     * @return datos del user que tiene el item
     */
    public UserRest getUser() {
        return this.user;
    }

    /**
     * Setter datos del user que tiene el item
     * @param user datos del user que tiene el item
     */
    public void setUser(UserRest user) {
        this.user = user;
    }

}

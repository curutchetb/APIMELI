package com.apiMeli.apiMeli.shared.dto;

import java.io.Serializable;
/**
 * DTO Data Transfer Object en relacion a los items favoritos.
 * Clase que vincula los distintos layers del codigo en relacion a los items favoritos.
 * @author Barbara
 *
 */
public class FavoritosDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemId;

    private int number;

    /**
     * Constructor
     * @param itemId id del item
     * @param number frecuencia de marcado como favorito
     */
    public FavoritosDto(String itemId, int number) {
        this.itemId = itemId;
        this.number = number;
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
     * Getter frecuencia de marcado como favorito
     * @return frecuencia de marcado como favorito
     */
    public int getnumber() {
        return this.number;
    }

    /**
     * Setter frecuencia de marcado como favorito
     * @param number frecuencia de marcado como favorito
     */
    public void setnumber(int number) {
        this.number = number;
    }

}

package com.apiMeli.apiMeli.models.responses;

/**
 *Clase encargada de dar respuesta de la lista de favoritos
 * y su respectivo numero de veces marcados como favoritos
 * @author Barbara
 *
 */
public class FavoritosRest {

    private String itemId;

    private int number;

    /**
     * Getter del id del item
     * @return id del item
     */
    public String getItemId() {
        return this.itemId;
    }

    /**
     * Setter del id del item
     * @param itemId
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * Getter del numero de veces item marcado como favorito
     * @returnnumero de veces item marcado como favorito
     */
    public int getnumber() {
        return this.number;
    }

    /**
     * Setter numero de veces item marcado como favorito
     * @param number numero de veces item marcado como favorito
     */
    public void setnumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "{\"" + getItemId() + "\":" +
                getnumber() +
                "}";
    }

}

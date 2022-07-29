package com.apiMeli.apiMeli.models.responses;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de dar respuesta de la lista de items posibles de compra por el monto del coupon
 * @author Barbara
 *
 */
public class CouponRest {

    private List<String> itemId = new ArrayList<String>();

    private double total;

    /**
     * Getter lista de items posibles de compra
     * @return lista de items posibles de compra
     */
    public List<String> getItemId() {
        return this.itemId;
    }

    /**
     * Setter lista de items posibles de compra
     * @param itemId lista de items posibles de compra
     */
    public void setItemId(List<String> itemId) {
        this.itemId = itemId;
    }

    /**
     * Getter precio total de la lista de items posibles de compra
     * @return precio total de la lista de items posibles de compra
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * Setter precio total de la lista de items posibles de compra
     * @param total precio total de la lista de items posibles de compra
     */
    public void setTotal(double total) {
        this.total = total;
    }

}

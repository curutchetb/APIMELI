package com.apiMeli.apiMeli.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO Data Transfer Object en relacion a los datos del coupon.
 * Clase que vincula los distintos layers del codigo en relacion a los datos del coupon.
 * @author Barbara
 *
 */
public class CouponDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> itemId = new ArrayList<String>();

    private double amount;

    /**
     * Getter de la lista de items
     * @return lista de items
     */
    public List<String> getItemId() {
        return this.itemId;
    }

    /**
     * Setter de la lista de items
     * @param itemId lista de items
     */
    public void setItemId(List<String> itemId) {
        this.itemId = itemId;
    }

    /**
     * Getter del monto del coupon
     * @return monto del coupon
     */
    public double getAmount() {
        return this.amount;
    }

    /**
     * Setter del monto del coupon
     * @param amount monto del coupon
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

}

package com.apiMeli.apiMeli.models.requests;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

/**
 * Clase encargada de convertir objeto Json a objeto JAVA
 * Recibe los datos enviados por el usuario para enciar datos del coupon
 * @author Barbara
 *
 */
public class CouponRequestModel {

    @NotEmpty(message = "La lista de items es obligatoria")
    private List<String> itemId = new ArrayList<String>();

    @NotNull(message = "El monto del coupon es obligatorio")
    @Range(min = 0, message = "EL monto es incorrecto")
    private double amount;

    /**
     * Getter de la lista de items a comprar
     * @return lista de items a comprar
     */
    public List<String> getItemId() {
        return this.itemId;
    }

    /**
     * Setter lista de items a comprar
     * @param itemId lista de items a comprar
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
     * Setter monto del coupon
     * @param amount monto del coupon
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

}

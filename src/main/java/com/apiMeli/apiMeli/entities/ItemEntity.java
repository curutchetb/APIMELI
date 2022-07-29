package com.apiMeli.apiMeli.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Clase encargada de la representacion en código java de los items en la base de datos.
 * Se definen los datos de los items que va a tener la base de datos
 * @author Barbara
 *
 */
@Entity(name = "items")
@EntityListeners(AuditingEntityListener.class) // se agrega fecha automaticamente. en main hay que habilitarlo
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String itemId;

    @Column(name = "price", nullable = false, scale = 2)
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    /**
     * Getter del user que tiene el item
     * @return user que tiene el item
     */
    public UserEntity getUser() {
        return this.user;
    }

    /**
     * Setter del user que tiene el item
     * @param user user que tiene el item
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * Getter del id autogenerado del item
     * @return id autogenerado del item
     */
    public long getId() {
        return this.id;
    }

    /**
     * Setter del id autogenerado del item
     * @param id id autogenerado del item
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter del id del item
     * @return id del item
     */
    public String getItemId() {
        return this.itemId;
    }

    /**
     * Setter del id del item
     * @param itemId id del item
     */
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * Getter del precio del item
     * @return precio del item
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Setter del precio del item
     * @param price precio del item
     */
    public void setPrice(double price) {
        this.price = price;
    }

}

package com.apiMeli.apiMeli.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase encargada de la representacion en código java del usuario en la base de datos.
 * Se definen los datos de los user que va a tener la base de datos
 * @author Barbara
 *
 */
@Entity(name = "users")
@Table(indexes = { @Index(columnList = "userId", name = "index_userId", unique = true),
        @Index(columnList = "email", name = "index_email", unique = true) })
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    // un usuario puede tener MUCHOS items por eso la lista
    // cascada porque si se borra un usuario hay que borrar todos los items
    // mappedBy es la propiedad que une las tablas
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ItemEntity> items = new ArrayList<>();

    /**
     * Getter de items
     * @return retorna lista de items
     */
    public List<ItemEntity> getItems() {
        return this.items;
    }

    /**
     * Setter de la lista de items
     * @param items items
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    /**
     * Getter del id autogenerado
     * @return id autogenerado
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
     * Getter del UserId público
     * @return retorna el UserId público
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Setter del UserId público
     * @param userId userId público
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Getter del nombre del user
     * @return nombre del user
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter del nombre del user
     * @param firstName nombre del user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter del apellido del user
     * @return apellido del user
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter del apellido del user
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
     * Setter del email del user
     * @param email email del user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter de la password encriptada
     * @return password encriptada
     */
    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

    /**
     * Setter de la password encriptada
     * @param encryptedPassword password encriptada
     */
    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}

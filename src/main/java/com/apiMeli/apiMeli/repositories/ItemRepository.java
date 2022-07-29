package com.apiMeli.apiMeli.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apiMeli.apiMeli.entities.ItemEntity;

/**
 * Clase repositorio donde nos permite dar funcionalidades CRUD a una entidad sin necesidad de crear 
 * las respectivas Querys.
 * Contiene metodos predefinidos
 * @author Barbara
 *
 */
@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Long> {

	/**
	 * Método para obtener lista de items segun el id del user
	 * @param userId id del user
	 * @return lista de items segun el id del user
	 */
    List<ItemEntity> getByUserId(long userId);

    /**
     * Método para obtener datos del item segun id
     * @param itemId id del item
     * @return datos del item segun id
     */
    ItemEntity findByItemId(String itemId);

    /**
     * Método para obtener la lista de todos los items del repositorio
     */
    List<ItemEntity> findAll();

}

package com.apiMeli.apiMeli.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apiMeli.apiMeli.entities.UserEntity;

/**
 * Clase repositorio donde nos permite dar funcionalidades CRUD a una entidad sin necesidad de crear las respectivas Querys.
 * Contiene metodos predefinidos
 * @author Barbara
 *
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

	/**
	 * Metodo para devolver entidad del user
	 * @param email email del user
	 * @return retorna el user entity
	 */
    public UserEntity findByEmail(String email);
}

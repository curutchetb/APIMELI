package com.apiMeli.apiMeli.services;

import java.util.List;

import com.apiMeli.apiMeli.shared.dto.FavoritosDto;
import com.apiMeli.apiMeli.shared.dto.ItemDto;

/**
 * Interfaz de Item Service donde se declaran los metodos a utilizarse en la clase ItemService
 * @author Barbara
 *
 */
public interface ItemServiceInterface {

	/**
	 * Metodo para obtener el item del usuario enviado
	 * @param itemId itemId el item
	 * @param userId userId publico del user
	 * @return retorna datos del item del user enviado
	 */
    public ItemDto getItem(String itemId, long userId);
    
    /**
     * Metodo para eliminar el item del usuario enviado
     * @param itemId itemId el item
     * @param userId userId publico del user
     */
    public void deleteItem(String itemId, long userId);
    
    /**
     * Metodo para obtener la lista de los primeros 5 items mas 
     * favoritos que fueron pidiendo los usuarios por el coupon
     * @return lista de los primeros 5 items mas favoriteados
     */
    public List<FavoritosDto> getItemsFavoritos();
}

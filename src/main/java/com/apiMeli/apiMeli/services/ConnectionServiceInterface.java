package com.apiMeli.apiMeli.services;

import java.util.Map;

/**
 * Interfaz de Connection Service donde se declaran los metodos a utilizarse en la clase ConnectionService
 * @author Barbara
 *
 */
public interface ConnectionServiceInterface {
    
	/**
	 * Método para obtener el item con su respectivo precio
	 * @param item id del item
	 * @return item con su respectivo precio
	 */
    public Map<String, Double> getItem(String item);
}
